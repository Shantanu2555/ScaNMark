package com.cdac.scanmark.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequest {
    private Long prn;
    private String name;
    private String email;
    private String macAddress;
}
