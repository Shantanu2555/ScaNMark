export const studentData = Array.from({ length: 10 }, (_, index) => ({
    prn: `PRN-${index + 1}`,
    name: `Student ${index + 1}`,
    todaysAttendance: `${Math.floor(Math.random() * 2) ? "Present" : "Absent"}`,
    overallAttendance: `${Math.floor(Math.random() * 100)}%`,
  }));
  
  export const facultyData = Array.from({ length: 10 }, (_, index) => ({
    facultyCode: `FC-${index + 1}`,
    name: `Faculty ${index + 1}`,
    email: `faculty${index + 1}@school.com`,
    dept: `Department ${index + 1}`,
  }));
  