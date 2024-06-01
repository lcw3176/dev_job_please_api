package com.comet.devjobplz.application.data.collector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectResult {

    private String companyName;

    private String title;

    private Integer minCareer;

    private Integer maxCareer;

    private LocalDateTime dueDate;

    private String source;
}
