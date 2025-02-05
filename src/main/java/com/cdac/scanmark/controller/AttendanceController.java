package com.cdac.scanmark.controller;

import com.cdac.scanmark.dto.AttendanceRequest;
import com.cdac.scanmark.entities.Attendance;
import com.cdac.scanmark.service.AttendanceService;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Update attendance
    @PutMapping("/update-attendance/{id}")
    public ResponseEntity<Attendance> updateAttendance(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody Attendance attendance) {
        Attendance updatedAttendance = attendanceService.updateAttendance(id, attendance);
        return ResponseEntity.ok(updatedAttendance);
    }

    // Get attendance by ID
    @GetMapping("/get-attendance-by-id/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        Attendance attendance = attendanceService.getAttendanceById(id);
        return ResponseEntity.ok(attendance);
    }

    // Get all attendance records
    @GetMapping("/get-all-attendance")
    public ResponseEntity<List<Attendance>> getAllAttendance(@RequestHeader("Authorization") String token) {
        List<Attendance> attendanceList = attendanceService.getAllAttendance();
        return ResponseEntity.ok(attendanceList);
    }

    // Delete attendance by ID
    @DeleteMapping("/delete-attendance/{id}")
    public ResponseEntity<String> deleteAttendance(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.ok("Attendance record deleted successfully");
    }

    // Additional endpoint: Get attendance by student
    @GetMapping("/get-attendance-by-student/{studentId}")
    public ResponseEntity<List<Attendance>> getAttendanceByStudent(@RequestHeader("Authorization") String token, @PathVariable Long studentId) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByStudent(studentId);
        return ResponseEntity.ok(attendanceList);
    }

    // Additional endpoint: Get attendance by lecture
    @GetMapping("/get-attendance-by-lecture-id/{lectureId}")
    public ResponseEntity<List<Attendance>> getAttendanceByLecture(@RequestHeader("Authorization") String token, @PathVariable Long lectureId) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByLecture(lectureId);
        return ResponseEntity.ok(attendanceList);
    }

    // @PostMapping("/mark-attendance")
    // public ResponseEntity<String> markAttendance(@RequestHeader("Authorization") String token,@RequestBody AttendanceRequest request) {
    //     try {
    //         attendanceService.markAttendance(request);
    //         return ResponseEntity.ok("Attendance marked successfully!");
    //     } catch (ValidationException e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    //     }
    // }

@CrossOrigin(origins = "http://localhost:3000") 
    @PostMapping("/mark-attendance")
public ResponseEntity<Map<String, Object>> markAttendance(@RequestHeader("Authorization") String token, 
                                                           @RequestBody AttendanceRequest request) {
    Map<String, Object> response = new HashMap<>();
    try {
        // Call service method to mark attendance
        attendanceService.markAttendance(request);
        
        // Prepare success response
        response.put("success", true);
        response.put("message", "Attendance marked successfully!");
        return ResponseEntity.ok(response);  // Return JSON with success
    } catch (ValidationException e) {
        // Prepare error response
        response.put("success", false);
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  // Return error as JSON
    }
}
}
