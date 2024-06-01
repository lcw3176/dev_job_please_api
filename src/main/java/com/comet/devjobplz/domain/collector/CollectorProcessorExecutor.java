package com.comet.devjobplz.domain.collector;


import com.comet.devjobplz.application.data.collector.CollectorType;
import com.comet.devjobplz.application.data.collector.DataCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollectorProcessorExecutor {


    private final ApplicationContext applicationContext;


    public void run(CollectorType type) {
        execute(type);
    }


    public void runAll() {
        for (CollectorType type : CollectorType.values()) {
            execute(type);
        }
    }

    private void execute(CollectorType type) {
        DataCollector collector = applicationContext.getBean(type.getCollector());

        collector.init();
        collector.process();
        collector.end();
    }

}
