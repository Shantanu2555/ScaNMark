package com.cdac.scanmark.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResetPasswordRequest {
    private String email;
    private String otp;
    private String newPassword;
    private String role ;

    public ResetPasswordRequest(String email, String otp, String newPassword, String role) {
        this.email = email;
        this.otp = otp;
        this.newPassword = newPassword;
        this.role = role ;

    }

    public ResetPasswordRequest() {
    }
}

