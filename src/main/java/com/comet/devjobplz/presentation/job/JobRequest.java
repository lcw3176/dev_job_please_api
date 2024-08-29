package com.comet.devjobplz.presentation.job;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobRequest {
    private double neLat;
    private double neLng;
    private double swLat;
    private double swLng;
}
