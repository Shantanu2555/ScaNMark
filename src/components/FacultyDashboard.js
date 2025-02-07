import React, { useState, useEffect, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import { 
  Container, Typography, Button, Select, MenuItem, Card, CardContent, CircularProgress 
} from '@mui/material';
import LogoutIcon from '@mui/icons-material/Logout';
import QrCodeIcon from '@mui/icons-material/QrCode';
import { QRCodeCanvas } from "qrcode.react"; // Import QRCodeCanvas
import '../styles/faculty_styles.css';

const API_BASE_URL = "http://localhost:8081";

const FacultyDashboard = () => {
  const [lectures, setLectures] = useState([]);
  const [selectedLecture, setSelectedLecture] = useState('');
  const [qrCode, setQrCode] = useState(null);
  const [timer, setTimer] = useState(60);
  const [faculty, setFaculty] = useState(null);
  const [loading, setLoading] = useState(true);
  const [qrCount, setQrCount] = useState(0);
  const [qrExpired, setQrExpired] = useState(false);  // Track QR Code expiration
  const navigate = useNavigate();
  const token = localStorage.getItem("studentToken"); // Replace with actual token

  const fetchFacultyDetails = useCallback(async () => {
    try {
      const response = await fetch(`${API_BASE_URL}/api/faculty/profile`, {
        method: 'GET',
        headers: { 'Authorization': `Bearer ${token}`, "Content-Type": 'application/json' },
      });
      if (response.ok) {
        setFaculty(await response.json());
      }
    } catch (error) {
      console.error('Error fetching faculty details:', error);
    }
  }, [token]);

  const fetchLectures = useCallback(async () => {
    if (!faculty) return;

    try {
      const facultyName = encodeURIComponent(faculty.name);
      const response = await fetch(`${API_BASE_URL}/api/lecture/lectures?facultyName=${facultyName}`, {
        method: 'GET',
        headers: { 'Authorization': `Bearer ${token}`, "Content-Type": 'application/json' },
      });

      const textResponse = await response.text();
      console.log('Raw API Response:', textResponse);

      try {
        const data = JSON.parse(textResponse);
        console.log('Parsed API Response:', data);
        if (response.ok) {
          setLectures(data);
        } else {
          console.error('API Error:', data);
        }
      } catch (jsonError) {
        console.error('Response is not valid JSON:', jsonError);
      }
    } catch (error) {
      console.error('Error fetching lectures:', error);
    } finally {
      setLoading(false);
    }
  }, [token, faculty]);

  useEffect(() => {
    fetchFacultyDetails();
  }, []);

  useEffect(() => {
    fetchLectures();
  }, [fetchLectures]);

  const generateQRCode = async () => {
    if (!selectedLecture || qrCount >= 2) return;

    if (!navigator.geolocation) {
      console.error("Geolocation is not supported by this browser.");
      return;
    }

    navigator.geolocation.getCurrentPosition(
      async (position) => {
        const { latitude, longitude } = position.coords;
        console.log("Machine Location:", latitude, longitude);

        try {
          const response = await fetch(`${API_BASE_URL}/api/faculty/generate-qr?lectureId=${selectedLecture}`, {
            method: 'POST',
            headers: {
              'Authorization': `Bearer ${token}`,
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({ latitude, longitude })
          });

          const textResponse = await response.text();
          console.log('Raw API Response:', textResponse);

          try {
            const data = JSON.parse(textResponse);
            if (response.ok && data.qrCode) {
              //setQrCode(data.qrCode); // QR Code will now be a text string
		setQrCode(`data:image/png;base64,${data.qrCode}`);
              setTimer(60);
              setQrExpired(false); // Reset expiration state
              setQrCount(prev => prev + 1);
            } else if (response.status === 409) {
              console.warn('QR Code already exists. Fetching the latest QR code...');
              await showQRCodeAgain();
            } else {
              console.error('Error:', data.error || 'Unknown error');
            }
          } catch (jsonError) {
            console.error('Response is not valid JSON:', textResponse);
          }
        } catch (error) {
          console.error('Error generating QR Code:', error);
        }
      },
      (error) => {
        console.error("Error getting location:", error);
      },
      { enableHighAccuracy: true, timeout: 10000, maximumAge: 0 }
    );
  };

  const showQRCodeAgain = async () => {
    try {
      const response = await fetch(`${API_BASE_URL}/api/faculty/show-qr-again/${selectedLecture}`, {
        method: 'GET',
        headers: { 'Authorization': `Bearer ${token}`, "Content-Type": 'application/json' },
      });

      const data = await response.json();

      if (response.ok && data.qrCode) {
        console.log("QR Code fetched successfully:", data.qrCode);
        const qrCodeData = data.qrCode.startsWith('data:image/png;base64,') 
            ? data.qrCode 
            : `data:image/png;base64,${data.qrCode}`;
        //setQrCode(data.qrCode);  // Set QR Code text
       setQrCode(qrCodeData);
        setTimer(60);
        setQrExpired(false); // Reset expiration state
      } else {
        console.error('Error fetching QR again:', data.error || 'Unknown error');
      }
    } catch (error) {
      console.error('Error fetching QR Code again:', error);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('studentToken');
    navigate('/');
  };

  // Timer countdown logic
  useEffect(() => {
    let interval = null;
    if (timer > 0 && qrCode && !qrExpired) {
      interval = setInterval(() => {
        setTimer((prevTimer) => prevTimer - 1);
      }, 1000);
    } else if (timer === 0) {
      setQrExpired(true); // Mark QR as expired when timer hits 0
      clearInterval(interval);
    }

    return () => {
      if (interval) clearInterval(interval); // Cleanup interval on component unmount or timer reach 0
    };
  }, [timer, qrCode, qrExpired]);

  return (
    <Container maxWidth="md" style={{ marginTop: 20, textAlign: 'center' }}>
      <Card raised>
        <CardContent>
          <Typography variant="h4">Faculty Dashboard</Typography>
          <Button variant="contained" color="secondary" startIcon={<LogoutIcon />} onClick={handleLogout}>
            Logout
          </Button>
        </CardContent>
      </Card>

      {loading ? (
        <CircularProgress style={{ marginTop: 20 }} />
      ) : (
        <>
          {faculty && (
            <Card style={{ marginTop: 20 }}>
              <CardContent>
                <Typography variant="h5">{faculty.name}</Typography>
                <Typography variant="body1">Department: {faculty.department}</Typography>
                <Typography variant="body2">Email: {faculty.email}</Typography>
              </CardContent>
            </Card>
          )}

          <Card style={{ marginTop: 20 }}>
            <CardContent>
              <Typography variant="h6">Select a Lecture</Typography>
              <Select
                fullWidth
                value={selectedLecture}
                onChange={(e) => setSelectedLecture(e.target.value)}
                displayEmpty
              >
                <MenuItem value="" disabled>Select a Lecture</MenuItem>
                {lectures.length > 0 ? (
                  lectures.map(({ id, subjectName, lectureTime }) => (
                    <MenuItem key={id} value={id}>
                      {subjectName} ({lectureTime})
                    </MenuItem>
                  ))
                ) : (
                  <MenuItem disabled>No lectures available</MenuItem>
                )}
              </Select>

              <Button variant="contained" color="primary" startIcon={<QrCodeIcon />} onClick={generateQRCode} disabled={!selectedLecture || qrCount >= 2 || qrExpired} style={{ marginTop: 20 }}>
                Generate QR Code
              </Button>

              {/* Show QR Again Button */}
              {!qrExpired && qrCode && (
                <Button
                  variant="contained"
                  color="secondary"
                  onClick={showQRCodeAgain}
                  style={{ marginTop: 20 }}
                >
                  Show QR Again
                </Button>
              )}
            </CardContent>
          </Card>

          {qrCode && !qrExpired && (
            <Card style={{ marginTop: 20, padding: 20, textAlign: 'center' }}>
              <Typography variant="h6">QR Code</Typography>
              <QRCodeCanvas value={qrCode} size={256} />
              <Typography variant="body2">Expires in {timer} seconds</Typography>
            </Card>
          )}

          {qrExpired && (
            <Card style={{ marginTop: 20, textAlign: 'center' }}>
              <Typography variant="h6" color="error">QR Code Expired</Typography>
            </Card>
          )}
        </>
      )}
    </Container>
  );
};

export default FacultyDashboard;
