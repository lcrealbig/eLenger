package com.api.lenger.domain.identity;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendConfirmationEmail(String email, String token) {
        System.out.println("Wysyłam email potwierdzenia do: " + email + " z tokenem: " + token);
    }
}