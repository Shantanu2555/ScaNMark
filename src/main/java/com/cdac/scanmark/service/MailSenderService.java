package com.cdac.scanmark.service;

import org.springframework.stereotype.Service;

public interface MailSenderService {
    void sendOtp(String toEmail, String otp) ;
}
