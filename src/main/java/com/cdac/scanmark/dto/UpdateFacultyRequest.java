package com.cdac.scanmark.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFacultyRequest {
    private String name ;
    private String email ;
    private String department ;
}
