const express = require('express');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(express.json());

const faculty = {
  name: "Dr. John Doe",
  department: "Computer Science",
  email: "john.doe@example.com",
  facultyCode: "FAC123"
};

const qrCodes = {
  "WPT-2024-10-30T09:00:00": "https://dummy-qr.com/wpt",
  "DS-2024-10-31T10:00:00": "https://dummy-qr.com/ds"
};

app.get('/api/faculty/profile', (req, res) => {
  res.json(faculty);
});

app.post('/api/faculty/generate-qr/:lectureId', (req, res) => {
  const { lectureId } = req.params;
  if (qrCodes[lectureId]) {
    res.json({ qrCode: qrCodes[lectureId] });
  } else {
    res.status(404).json({ error: "Lecture not found" });
  }
});

app.get('/api/faculty/show-qr-again/:lectureId', (req, res) => {
  const { lectureId } = req.params;
  if (qrCodes[lectureId]) {
    res.json({ qrCode: qrCodes[lectureId] });
  } else {
    res.status(404).json({ error: "QR Code not found" });
  }
});

app.listen(5000, () => {
  console.log('Mock API running on http://localhost:5000');
});
