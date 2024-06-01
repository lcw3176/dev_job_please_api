package com.comet.devjobplz.application.data.collector;

import com.comet.devjobplz.domain.collector.CollectorProcessorExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataCollectorUseCase {

    private final CollectorProcessorExecutor executor;

    public void execute() {
        executor.run(CollectorType.JOB_KOREA);
    }
}
