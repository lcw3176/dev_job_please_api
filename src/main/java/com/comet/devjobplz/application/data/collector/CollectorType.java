package com.comet.devjobplz.application.data.collector;

import com.comet.devjobplz.application.data.collector.jobkorea.JobKoreaDataCollector;
import com.comet.devjobplz.domain.joblink.SourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CollectorType {
    JOB_KOREA(SourceType.JOB_KOREA, JobKoreaDataCollector.class);

    private final SourceType sourceType;
    private final Class<? extends DataCollector> collector;
}
