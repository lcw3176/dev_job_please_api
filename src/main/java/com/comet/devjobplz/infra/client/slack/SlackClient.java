package com.comet.devjobplz.infra.client.slack;


import com.comet.devjobplz.infra.client.ApiHandler;
import com.comet.devjobplz.infra.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class SlackClient {

    private final RestClient slackRestClient;

    public void sendMessage(String title, String message) {
        SlackMessage slackMessage = SlackMessage.builder()
                .title(title)
                .message(makeTransmissible(message))
                .build();

        sendSlackMessage(slackMessage);
    }

    public void sendMessage(ApiException e) {
        SlackMessage slackMessage = SlackMessage.builder()
                .title(e.getCode().getMessage())
                .message(makeTransmissible(e))
                .build();

        sendSlackMessage(slackMessage);
    }

    public void sendMessage(Throwable e) {
        SlackMessage slackMessage = SlackMessage.builder()
                .title(e.getClass().getName())
                .message(makeTransmissible(e))
                .build();

        sendSlackMessage(slackMessage);
    }

    private String makeTransmissible(Throwable e) {
        String stackTrace = Arrays.toString(e.getStackTrace());

        return makeTransmissible(stackTrace);
    }

    private String makeTransmissible(String stackTrace) {
        int len = Math.min(stackTrace.length(), 1700);

        return stackTrace.substring(0, len);
    }

    private void sendSlackMessage(SlackMessage exception) {
        String message = SlackMessageFormatter.makeExceptionMessage(exception);

        ApiHandler.handle(() -> slackRestClient.post()
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .body(message)
                .retrieve()
                .body(String.class));
    }


}
