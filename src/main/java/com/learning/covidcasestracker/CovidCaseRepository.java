package com.learning.covidcasestracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Specify the (T) type of object that we want this repo to work with, and (ID) for the type that we want.
@Repository
public interface CovidCaseRepository extends JpaRepository<CovidCase, Integer> {

    //SELECT * FROM cases WHERE email = ?
    @Query("SELECT c FROM CovidCase c WHERE c.email = ?1")
    Optional<CovidCase> findCovidCaseByEmail(String email);
}