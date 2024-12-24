package com.cdac.scanmark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttendanceRequest {
    private Long studentPrn;           // Student's PRN
    private String signedQrContent;    // Signed QR content from student
    private Double latitude;           // Student's current latitude
    private Double longitude; 
    private Long lectureId ;         // Student's current longitude
}

