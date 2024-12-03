package com.cdac.scanmark.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequest {
    private String name ;
    private String email ;
    private String macAddress ;
}
