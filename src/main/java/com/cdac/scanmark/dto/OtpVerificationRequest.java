package com.cdac.scanmark.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OtpVerificationRequest {
    private String email;
    private String otp;

    public OtpVerificationRequest(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    public OtpVerificationRequest(){}
}
