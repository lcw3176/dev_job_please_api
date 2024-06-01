package com.comet.devjobplz.application.data.collector.jobkorea;

import com.comet.devjobplz.application.data.collector.CollectResult;
import com.comet.devjobplz.application.data.collector.CollectorType;
import com.comet.devjobplz.application.data.collector.DataCollector;
import com.comet.devjobplz.domain.collector.CollectorProcessor;
import com.microsoft.playwright.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class JobKoreaDataCollector implements DataCollector {

    private final CollectorProcessor executor;

    @Override
    public void init() {
        executor.beforeStart(CollectorType.JOB_KOREA.name());
    }

    @Override
    public void process() {
        CollectResult result = executor.execute((browser) -> {
            Page page = browser.newPage();
            page.navigate("https://www.jobkorea.co.kr/recruit/joblist?menucode=duty");

            return CollectResult.builder().build();
        });
    }

    @Override
    public void end() {
        executor.afterEnd(CollectorType.JOB_KOREA.name());
    }

    @Override
    public CollectResult getResult() {
        return null;
    }

}
