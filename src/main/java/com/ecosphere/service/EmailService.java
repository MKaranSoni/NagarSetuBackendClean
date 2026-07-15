package com.ecosphere.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtp(String email, String otp) {

    try {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP Verification");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);

        System.out.println("MAIL SUCCESS");

    } catch (Exception e) {

        System.out.println("MAIL FAILED");
        e.printStackTrace();

        throw new RuntimeException(e);
    }
}
}