package com.codegym.c11.configuration;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
public class SendGridConfig {

    @Value("${app.sendgrid.key}")
    private String appKey;

    @Bean
    public SendGrid getSendGrid() {
        return new SendGrid(appKey);
    }
}
