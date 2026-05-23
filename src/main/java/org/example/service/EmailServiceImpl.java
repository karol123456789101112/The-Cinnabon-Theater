package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String receiver, String content, String subject) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("the-cinnabon-theater@gmail.com");
        mail.setTo(receiver);
        mail.setSubject(subject);
        mail.setText(content);
        javaMailSender.send(mail);
    }

}