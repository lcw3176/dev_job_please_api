package com.comet.devjobplz.domain.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;


    public List<Company> findAllByCoordsInRange(double neLat, double neLng, double swLat, double swLng) {
        return companyRepository.findAllByLatBetweenAndLngBetween(swLat, neLat, swLng, neLng);
    }
}
