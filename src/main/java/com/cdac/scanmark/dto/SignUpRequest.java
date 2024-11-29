package com.cdac.scanmark.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignUpRequest {
    private String email ;
    private String name ;
    private String password ;
}
