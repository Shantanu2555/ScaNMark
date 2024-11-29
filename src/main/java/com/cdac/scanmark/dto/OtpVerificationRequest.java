package com.cdac.scanmark.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OtpVerificationRequest {
    private Long coordinatorId;
    private String otp;

    public OtpVerificationRequest(Long coordinatorId, String otp) {
        this.coordinatorId = coordinatorId;
        this.otp = otp;
    }

    public OtpVerificationRequest(){}
}
