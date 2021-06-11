package com.learning.covidcasestracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "{PatientID}")
    public Optional<CovidCase> getCovidCaseById(@PathVariable("PatientID") Integer PatientID) {
        return caseService.getCovidCaseByID(PatientID);
    }

    @PostMapping
    public void registerNewCase(@RequestBody CovidCase covidCase) {
        caseService.addNewCovidCase(covidCase);
    }

    @DeleteMapping(path = "{PatientID}")
    public void deleteCovidCase(@PathVariable("PatientID") Integer PatientID) {
        caseService.deleteCase(PatientID);
    }

    @PutMapping(path = "{PatientID}")
    public void updateCovidCase(
            @PathVariable("PatientID") Integer PatientID,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) LocalDate birthday,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String casePeriod,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) boolean underlyingDisease
    ) {
        caseService.updateCovidCase(
                PatientID,
                fullName,
                birthday,
                email,
                casePeriod,
                city,
                underlyingDisease
                );
    }

}
