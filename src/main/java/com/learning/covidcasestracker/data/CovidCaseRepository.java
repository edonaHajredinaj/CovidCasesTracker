package com.learning.covidcasestracker.data;

import com.learning.covidcasestracker.data.model.CovidCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Specify the (T) type of object that we want this repo to work with, and (ID) for the type that we want.
@Repository
public interface CovidCaseRepository extends JpaRepository<CovidCase, Integer> {

    //SELECT * FROM cases WHERE email = ? also the nr 1 after ?, means it returns 1 email.
    @Query("SELECT c FROM CovidCase c WHERE c.email = ?1")
    Optional<CovidCase> findCovidCaseByEmail(String email);

    @Query("SELECT COUNT(PatientID) FROM CovidCase WHERE deceased = false AND recovered = false AND deactivated = false")
    Integer getAllActiveCases();

    @Query("SELECT COUNT(PatientID) FROM CovidCase")
    Integer getTotalCovidCasesNumber();

//    @Query("SELECT COUNT(PatientID) FROM CovidCase WHERE deceased = true")
//    Integer findAllByDeceased();
//
//    @Query("SELECT COUNT(PatientID) FROM CovidCase WHERE recovered = true")
//    Integer findAllByRecovered();
//
//    @Query("SELECT COUNT(PatientID) FROM CovidCase WHERE deactivated = true ")
//    Integer findAllByDeactivated();


}