package com.cdac.scanmark.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddFacultyRequest {
    private String facultyCode ;
    private String name ;
    private String email ;
    private String department ;
}
