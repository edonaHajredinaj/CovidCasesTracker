package com.learning.covidcasestracker.controller;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CovidCaseRequest {
    private Integer PatientID;
    private String fullName;
    private LocalDate birthday;
    private String email;
    private String casePeriod;
    private String city;
    private boolean underlyingDisease;
}
