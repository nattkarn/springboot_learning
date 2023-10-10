package com.testing.api.backend.service;


import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Value("${app.email.form}")
    private String form;
    public void send(String to,String subject, String html){
        MimeMessagePreparator message = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            helper.setFrom(form);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html,true);
        };
        mailSender.send(message);
    }
}
