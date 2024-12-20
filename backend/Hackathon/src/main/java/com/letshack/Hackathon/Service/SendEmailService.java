package com.letshack.Hackathon.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmailId;

    public void sendEmail(String recipientEmailId,String body,String subject){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmailId);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(recipientEmailId);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
    }
}
