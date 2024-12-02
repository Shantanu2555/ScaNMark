package com.cdac.scanmark.dto;

import com.cdac.scanmark.entities.Attendance;
import com.cdac.scanmark.entities.Student;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class StudentHistoryResponse {
    private Student student ;
    private List<Attendance> attendanceList ;

    public StudentHistoryResponse(Student student, List<Attendance> attendanceList) {
        this.student = student;
        this.attendanceList = attendanceList;
    }

    public StudentHistoryResponse(){}
}
