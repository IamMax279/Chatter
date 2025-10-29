package com.max420.chatter_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${service.url.auth}")
    private String authServiceUrl;
    @Value("${service.url.user}")
    private String userServiceUrl;

    @Bean
    public WebClient authWebClient() {
        return WebClient.builder()
                .baseUrl(authServiceUrl)
                .build();
    }

    @Bean
    public WebClient userWebClient() {
        return WebClient.builder()
                .baseUrl(authServiceUrl)
                .build();
    }
}
