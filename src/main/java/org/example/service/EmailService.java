package org.example.service;

public interface EmailService {
    void sendMail(String receiver, String content, String subject);
}
