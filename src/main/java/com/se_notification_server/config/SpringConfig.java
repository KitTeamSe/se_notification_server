package com.se_notification_server.config;

import com.se_notification_server.repository.FcmRepository;
import com.se_notification_server.service.FcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {


    private final FcmRepository fcmRepository;

    @Autowired
    public SpringConfig(FcmRepository fcmRepository) {
        this.fcmRepository = fcmRepository;
    }

    @Bean
    public FcmService AccountService() {
        return new FcmService(fcmRepository);
    }
}