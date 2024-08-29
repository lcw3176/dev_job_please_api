package com.comet.devjobplz.application.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {
    private long id;

    private double lat;

    private double lng;

    private String companyName;

    private int jobCount;
}
