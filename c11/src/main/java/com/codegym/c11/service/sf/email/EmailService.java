package com.codegym.c11.service.sf.email;

import com.codegym.c11.model.dto.response.EmailResponseDto;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class EmailService {

    @Value("${app.sendgrid.templateId}")
    private String templateId;

    @Value("${app.sendgrid.key}")
    private String sendGridApiKey;

    @Autowired
    private SendGrid sendGrid;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleEmail(EmailResponseDto emailResponseDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hnlinhtkd@gmail.com");
        message.setTo(emailResponseDto.getTo());
        message.setText(emailResponseDto.getBody());
        message.setSubject(emailResponseDto.getSubject());
        javaMailSender.send(message);
        System.out.println("Mail Sent...");
    }

    public String sendEmailBySendGrid(String email) {
        try {
            Mail mail = prepareMail(email);

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("/v3/mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            if (response != null) {
                System.out.println("Response code from SendGrid: " + response.getHeaders());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Mail was failed to send.";
        }
        return "Mail was sent succesfully.";
    }

    public Mail prepareMail(String email) {
        Mail mail = new Mail();

        Email fromEmail = new Email();
        fromEmail.setEmail("cg.cinema11@gmail.com");

        Email to = new Email();
        to.setEmail(email);

        Personalization personalization = new Personalization();
        personalization.addTo(to);
        mail.setTemplateId(templateId);

        return mail;
    }

    public void sendEmailThroughFreeSmtp(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cg.cinema11@gmail.com");
        message.setSentDate(new Date());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        try {
            javaMailSender.send(message);
            System.out.println("Message succesfully sent to " + to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEmailTest(String to, String subject, String body) {
        Email from = new Email("cg.cinema11@gmail.com");
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, toEmail, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            if (response != null) {
                System.out.println("Response code from SendGrid: " + response.getHeaders());
            }
        } catch (Exception ex) {
            System.out.println("Failed to send email: " + ex.getMessage());
        }
    }
}