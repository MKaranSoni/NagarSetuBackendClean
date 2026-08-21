package com.ecosphere.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Resend resend;

    public EmailService(@Value("${RESEND_API_KEY}") String apiKey) {
        this.resend = new Resend(apiKey);
    }

    public void sendOtp(String email, String otp) {

        try {

            CreateEmailOptions params = CreateEmailOptions.builder()
                    .from("NagarSetu <onboarding@resend.dev>")
                    .to(email)
                    .subject("NagarSetu OTP Verification")
                    .html("""
                            <h2>NagarSetu OTP Verification</h2>
                            <p>Your OTP is:</p>
                            <h1>%s</h1>
                            <p>This OTP is valid for 5 minutes.</p>
                            <p>If you did not request this OTP, please ignore this email.</p>
                            """.formatted(otp))
                    .build();

            var response = resend.emails().send(params);

            System.out.println("MAIL SUCCESS");
            System.out.println("Resend Email ID: " + response.getId());

        } catch (ResendException e) {

            System.out.println("MAIL FAILED");
            e.printStackTrace();

            throw new RuntimeException("Failed to send OTP email", e);
        }
    }
}