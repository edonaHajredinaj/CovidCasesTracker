package com.learning.covidcasestracker.controller;

import com.learning.covidcasestracker.CovidCaseService;
import com.learning.covidcasestracker.data.model.CovidCase;
import com.learning.covidcasestracker.statistics.CovidCaseStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/covidcases")
public class CovidCaseController {

    private final CovidCaseService caseService;

    @Autowired
    public CovidCaseController(CovidCaseService caseService) {
        this.caseService = caseService;
    }

    @GetMapping
    public List<CovidCase> getCovidCases() {
        return caseService.getCovidCases();
    }

    @GetMapping("/stat")
    public CovidCaseStatistics getCovidCasesStat() {
        return caseService.getAllCovidCaseStat();
    }


    @GetMapping(path = "{patientID}")
    public CovidCase getCovidCaseById(@PathVariable("patientID") Integer PatientID) {
        return caseService.getCovidCaseByIdOrThrow(PatientID);
    }

    @PostMapping("/create")
    public void registerNewCase(@RequestBody CovidCase covidCase) {
        caseService.addNewCovidCase(covidCase);
    }

    @DeleteMapping(path = "{patientID}")
    public void deleteCovidCase(@PathVariable("patientID") Integer patientID) {
        caseService.deleteCase(patientID);
    }

    @PutMapping(path = "{patientID}")
    public void updateCovidCase(
            @PathVariable("patientID") Integer patientID,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) LocalDate birthday,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String casePeriod,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) boolean underlyingDisease
    ) {
        caseService.updateCovidCase(
                CovidCaseRequest.builder()
                        .PatientID(patientID)
                        .fullName(fullName)
                        .casePeriod(casePeriod)
                        .birthday(birthday)
                        .email(email)
                        .city(city)
                        .underlyingDisease(underlyingDisease)
                        .build()
        );
    }
}
