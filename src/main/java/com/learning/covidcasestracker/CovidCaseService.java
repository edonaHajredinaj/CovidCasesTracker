package com.learning.covidcasestracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CovidCaseService {

    private final CovidCaseRepository caseRepository;

    @Autowired
    public CovidCaseService(CovidCaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public List<CovidCase> getCovidCases() {
        return caseRepository.findAll();
    }

    public Optional<CovidCase> getCovidCaseByID(Integer PatientID) {
        if (!caseRepository.existsById(PatientID)) {
            throw new IllegalStateException("case with id " + PatientID + " does not exist");
        }
       return caseRepository.findById(PatientID);

    }

    public void addNewCovidCase(CovidCase covidCase) {
        Optional<CovidCase> caseOptional = caseRepository
                .findCovidCaseByEmail(covidCase.getEmail());
        if (caseOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        caseRepository.save(covidCase);
    }

    public void deleteCase(Integer PatientID) {
        boolean exists = caseRepository.existsById(PatientID);
        if (!exists) {
            throw new IllegalStateException("case with id " + PatientID + " does not exist");
        }
        caseRepository.deleteById(PatientID);
    }

    @Transactional
    public void updateCovidCase(Integer patientID,
                                String fullName,
                                LocalDate birthday,
                                String email,
                                String casePeriod,
                                String city,
                                boolean underlyingDisease) {

        LocalDate dt1 = LocalDate.parse("1900-01-01");
        CovidCase covidCase = caseRepository.findById(patientID)
                .orElseThrow(() -> new IllegalStateException(
                        "covid case with id " + patientID + " does not exist"));

        if (fullName != null &&
                fullName.length() > 0 &&
                !Objects.equals(covidCase.getFullName(), fullName)) {

                covidCase.setFullName(fullName);
        }
        if (birthday != null &&
                birthday.isAfter(dt1) &&
                !Objects.equals(covidCase.getBirthday(), birthday)) {

            covidCase.setBirthday(birthday);
        }
        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(covidCase.getEmail(), email)) {
            Optional<CovidCase> caseOptional =
                    caseRepository.findCovidCaseByEmail(email);
            if (caseOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            covidCase.setEmail(email);
        }
        if (casePeriod != null &&
                casePeriod.length() > 0 &&
                !Objects.equals(covidCase.getCasePeriod(), casePeriod)) {

            covidCase.setCasePeriod(casePeriod);
        }
        if (city != null &&
                city.length() > 0 &&
                !Objects.equals(covidCase.getCity(), city)) {

            covidCase.setCity(city);
        }
        if (!Objects.equals(covidCase.isUnderlyingDisease(), underlyingDisease)) {

            covidCase.setUnderlyingDisease(underlyingDisease);
        }

    }
}
