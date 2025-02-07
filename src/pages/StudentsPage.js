import { useState, useEffect, useMemo } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  TablePagination,
  CircularProgress,
  Alert,
} from "@mui/material";
import "../styles/studentPage.css";

const StudentsPage = ({ searchQuery = "" }) => {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);

  useEffect(() => {
    const fetchStudents = async () => {
      try {
        const response = await fetch("http://localhost:8081/api/students/attendance-summary", {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("studentToken")}`, // Replace with actual token if needed
          },
        });

        if (!response.ok) {
          throw new Error("Failed to fetch student attendance data");
        }

        const data = await response.json();
        setStudents(data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchStudents();
  }, []);

  const filteredStudents = useMemo(() => {
    return students.filter((student) =>
      student.prn.toString().toLowerCase().includes(searchQuery.toLowerCase())
    );
  }, [searchQuery, students]);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  if (loading) {
    return (
      <div className="loading-container">
        <CircularProgress />
        <p>Loading student attendance...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="error-container">
        <Alert severity="error">{error}</Alert>
      </div>
    );
  }

  return (
    <Paper className="students-container">
      <TableContainer className="table-container">
        <Table>
          <TableHead>
            <TableRow className="table-header">
              <TableCell>PRN</TableCell>
              <TableCell>Name</TableCell>
              <TableCell>Today's Attendance</TableCell>
              <TableCell>Overall Attendance</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredStudents.length === 0 ? (
              <TableRow>
                <TableCell colSpan={4} align="center">
                  <Alert severity="info">No students found.</Alert>
                </TableCell>
              </TableRow>
            ) : (
              filteredStudents
                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                .map((student) => (
                  <TableRow key={student.prn} className="table-row">
                    <TableCell>{student.prn}</TableCell>
                    <TableCell>{student.name}</TableCell>
                    <TableCell className={student.todayAttendance === "present" ? "present" : "absent"}>
                      {student.todayAttendance}
                    </TableCell>
                    <TableCell>{student.overallAttendance.toFixed(2)}%</TableCell>
                  </TableRow>
                ))
            )}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        className="pagination"
        rowsPerPageOptions={[5, 10, 15]}
        component="div"
        count={filteredStudents.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
};

export default StudentsPage;
