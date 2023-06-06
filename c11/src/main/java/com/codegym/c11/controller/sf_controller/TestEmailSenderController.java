package com.codegym.c11.controller.sf_controller;

import com.codegym.c11.model.dto.response.EmailResponseDto;
import com.codegym.c11.service.sf.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/test/email")
public class TestEmailSenderController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendGrid/{email}")
    public ResponseEntity<?> sendEmail(@PathVariable(value = "email", required = true) String email) {
        String emailResponse = emailService.sendEmailBySendGrid(email);
        if (emailResponse == "Mail was sent succesfully.") {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/sendMail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailResponseDto emailResponseDto) {
        try {
            String to = emailResponseDto.getTo();
            String subject = emailResponseDto.getSubject();
            String body = emailResponseDto.getBody();

            emailService.sendEmailThroughFreeSmtp(to, subject, body);
//            emailService.sendEmailTest(to, subject, body);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/send-email")
    public ResponseEntity<?> triggerMail(@RequestBody EmailResponseDto emailDto) throws MessagingException {
        emailService.sendSimpleEmail(emailDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
