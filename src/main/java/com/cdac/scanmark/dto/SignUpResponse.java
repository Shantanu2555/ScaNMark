package com.cdac.scanmark.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignUpResponse {
    private String message ;
    private Long id ;

    public SignUpResponse(String message, Long id) {
        this.message = message;
        this.id = id;
    }

    public SignUpResponse() {
    }
}
