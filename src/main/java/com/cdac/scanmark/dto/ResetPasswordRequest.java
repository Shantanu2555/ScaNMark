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

    public ResetPasswordRequest(String email, String otp, String newPassword) {
        this.email = email;
        this.otp = otp;
        this.newPassword = newPassword;
    }

    public ResetPasswordRequest() {
    }
}

