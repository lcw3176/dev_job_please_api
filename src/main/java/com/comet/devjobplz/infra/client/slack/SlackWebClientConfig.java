package com.comet.devjobplz.infra.client.slack;

import com.comet.devjobplz.infra.client.ExternalApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class SlackWebClientConfig {


    @Value("${client.slack.url}")
    private String slackUrl;
    

    @Bean
    public RestClient slackRestClient() {
        return ExternalApiClient.getClient(slackUrl);
    }
}
