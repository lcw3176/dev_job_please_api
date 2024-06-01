package com.comet.devjobplz.domain.joblink;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SourceType {
    JOB_KOREA("잡코리아"),
    SARAMIN("사람인"),
    PROGRAMMERS("프로그래머스"),
    WANTED("원티드");

    private final String korean;
}
