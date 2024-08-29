package com.comet.devjobplz.application.job;

import com.comet.devjobplz.domain.company.Company;
import com.comet.devjobplz.domain.company.CompanyService;
import com.comet.devjobplz.domain.job.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JobUseCase {

    private final JobService jobService;
    private final CompanyService companyService;

    // trx
    public List<JobResponse> getCompanies(double neLat, double neLng, double swLat, double swLng) {
        List<Company> companies = companyService.findAllByCoordsInRange(neLat, neLng, swLat, swLng);

        return companies.stream()
                .map(i -> JobResponse.builder()
                        .id(i.getId())
                        .lat(i.getLat())
                        .lng(i.getLng())
                        .companyName(i.getCompanyName())
                        .jobCount(i.getJobCount())
                        .build())
                .collect(Collectors.toList());
    }
}
