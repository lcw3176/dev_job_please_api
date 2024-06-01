package com.comet.devjobplz.presentation;

import com.comet.devjobplz.application.data.collector.CollectorType;
import com.comet.devjobplz.application.data.collector.DataCollectorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final DataCollectorUseCase dataCollectorUseCase;

    @GetMapping("/job-korea")
    public void test() {
        dataCollectorUseCase.execute(CollectorType.JOB_KOREA);
    }
}
