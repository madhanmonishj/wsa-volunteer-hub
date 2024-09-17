package com.example.wsa.contact;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Contact contact) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("isocmatchtest10@gmail.com"); // Replace with your specified email
        helper.setSubject("New Contact Form Submission");
        String emailContent = "Name: " + contact.getName() + "\n" +
                "Email: " + contact.getEmail() + "\n" +
                "Subject: " + contact.getSubject() + "\n" +
                "Message: " + contact.getMessage();
        helper.setText(emailContent);
        javaMailSender.send(message);
    }
}
