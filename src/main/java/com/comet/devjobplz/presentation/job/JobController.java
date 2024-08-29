package com.comet.devjobplz.presentation.job;

import com.comet.devjobplz.application.job.JobResponse;
import com.comet.devjobplz.application.job.JobUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job")
@CrossOrigin("*")
public class JobController {

    private final JobUseCase jobUseCase;

    @GetMapping
    public ResponseEntity<List<JobResponse>> test(JobRequest jobRequest) {
        List<JobResponse> response = jobUseCase.getCompanies(jobRequest.getNeLat(), jobRequest.getNeLng(),
                jobRequest.getSwLat(), jobRequest.getSwLng());

        return ResponseEntity.ok(response);
    }
}
