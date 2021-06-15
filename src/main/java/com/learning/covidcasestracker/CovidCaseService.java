package com.learning.covidcasestracker;

import com.learning.covidcasestracker.controller.CovidCaseRequest;
import com.learning.covidcasestracker.data.model.CovidCase;
import com.learning.covidcasestracker.data.CovidCaseRepository;
import com.learning.covidcasestracker.statistics.CovidCaseStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

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

    public void addNewCovidCase(CovidCase covidCase) {
        Optional<CovidCase> caseOptional = caseRepository
                .findCovidCaseByEmail(covidCase.getEmail());
        if (caseOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        caseRepository.save(covidCase);
    }

    public CovidCase getCovidCaseByIdOrThrow(Integer patientID) {
        return caseRepository.findById(patientID)
                .orElseThrow(() -> new IllegalStateException(
                        "covid case with id " + patientID + " does not exist"));
    }

//    public void deleteCase(Integer PatientID) {
//        getCovidCaseByIdOrThrow(PatientID);
//        caseRepository.deleteById(PatientID);
//    }

    @Transactional
    public void updateCovidCase(CovidCaseRequest request) {
        CovidCase covidCase = getCovidCaseByIdOrThrow(request.getPatientID());

        if (isValid(request.getFullName(), covidCase.getFullName())) {
            covidCase.setFullName(request.getFullName());
        }
        if (request.getBirthday() != null &&
                request.getBirthday().isAfter(LocalDate.parse("1900-01-01")) &&
                !Objects.equals(covidCase.getBirthday(), request.getBirthday())) {
            covidCase.setBirthday(request.getBirthday());
        }
        if (isValid(request.getEmail(), covidCase.getEmail())) {
            Optional<CovidCase> caseFromDb =
                    caseRepository.findCovidCaseByEmail(request.getEmail());
            if (caseFromDb.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            covidCase.setEmail(request.getEmail());
        }
        if (isValid(request.getCasePeriod(), covidCase.getCasePeriod())) {
            covidCase.setCasePeriod(request.getCasePeriod());
        }
        if (isValid(request.getCity(), covidCase.getCity())) {
            covidCase.setCity(request.getCity());
        }

        covidCase.setUnderlyingDisease(request.isUnderlyingDisease());
    }

    public CovidCaseStatistics getAllCovidCaseStat() {
        return CovidCaseStatistics.builder()
                .deactivated(getCount(CovidCase::isDeactivated))
                .recovered(getCount(CovidCase::isRecovered))
                .deceased(getCount(CovidCase::isDeceased))
                .build();
    }



    private boolean isValid(String newValue, String oldValue) {
        return newValue != null &&
                newValue.length() > 0 &&
                !Objects.equals(oldValue, newValue);
    }

    private long getCount(Predicate<CovidCase> predicate) {
        return caseRepository.findAll().stream()
                .filter(predicate)
                .count();
    }
}
