package com.comet.devjobplz.domain.collector;

import com.comet.devjobplz.application.data.collector.CollectResult;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@Slf4j
public class CollectorProcessor {


    public void beforeStart(String value) {
        log.info("{}", value);
        log.info("작업 시작");
    }

    public CollectResult execute(Function<Browser, CollectResult> function) {
        try (Playwright playwright = Playwright.create()) {
            return function.apply(playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
        }
    }

    public void afterEnd(String value) {
        log.info("{}", value);
        log.info("작업 종료");
    }


}
