package com.comet.devjobplz.domain.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAllByLatBetweenAndLngBetween(double latA, double latB, double lngA, double lngB);
}
