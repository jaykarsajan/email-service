package com.example.email_service.service;

import com.example.email_service.vo.EmailRequest;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.nio.file.Files;

@Service
public class EmailService {

    //Configure an EmailTemplate entity in the database with fields for subject, to, cc, and templateBody. The to and cc fields will store multiple email addresses separated by the | delimiter. Implement a JPA repository to fetch the EmailTemplate by subject. Parse the to and cc fields to extract individual email addresses. Then, trigger an email using the template body to all users listed in the to and cc fields accordingly

    //For testing we configure info in properties file

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Value("${email.subject}")
    private String subject;

    @Value("${email.cc}")
    private String cc;

    @Value("${email.to}")
    private String to;

    @Value("${email.from}")
    private String from;

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public Boolean sendEmail(EmailRequest request) {
        logger.info("Enter in EmailService sendEmail");
        try {
            String htmlBody = loadTemplate().replace("{{msg}}", request.getContent());
            logger.info("htmlBody :: "+htmlBody);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            if (!cc.isBlank()) helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            logger.error("Inside EmailService sendEmail catch --> " + e.getMessage());
            return false;
        }
    }

    private String loadTemplate() {
        logger.info("Enter in EmailService loadTemplate");
        try {
            Resource resource = new ClassPathResource("templates/email-template.html");
            return new String(Files.readAllBytes(resource.getFile().toPath()));
        } catch (Exception e) {
            logger.error("Inside EmailService loadTemplate catch --> " + e.getMessage());
           return null;
        }
    }


}
