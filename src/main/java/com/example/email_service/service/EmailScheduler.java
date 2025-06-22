package com.example.email_service.service;

import com.example.email_service.vo.EmailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class EmailScheduler {

    private static final Logger logger = LoggerFactory.getLogger(EmailScheduler.class);

    private final EmailService emailService;

    public EmailScheduler(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 45 17 * * *")
    public void scheduledEmailTask() {
        logger.info("Scheduled email task triggered at "+ Timestamp.from(Instant.now()));

        EmailRequest request = new EmailRequest();
        request.setTemplateName("default");
        request.setContent("This is a scheduled email sent by cron");

        boolean sent = emailService.sendEmail(request);
        if (sent) {
            logger.info("Scheduled email sent successfully.");
        } else {
            logger.error("Failed to send scheduled email.");
        }
    }
}
