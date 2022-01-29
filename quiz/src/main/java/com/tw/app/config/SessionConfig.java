package com.tw.app.config;

import com.tw.app.services.SessionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class SessionConfig
{
    @Bean @SessionScope
    public SessionModel getModel()
    {
        return new SessionModel();
    }
}
