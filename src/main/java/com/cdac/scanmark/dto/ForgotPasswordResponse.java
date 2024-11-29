package com.cdac.scanmark.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ForgotPasswordResponse {
    private String message ;
    private boolean success ;

    public ForgotPasswordResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public ForgotPasswordResponse() {
    }
}
