package com.learning.covidcasestracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Calendar.*;

@Configuration
public class CovidCaseConfig {

    @Bean
    CommandLineRunner commandLineRunner(CovidCaseRepository repository) {
        return args -> {
            CovidCase keksi = new CovidCase(

                    "Filan Keksi",
                    LocalDate.of(1999, APRIL,5),
                    "filankeksi@email.com",
                    DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()),
                    "Prizren",
                    false
            );

            CovidCase eleri = new CovidCase(

                    "William Eleri",
                    LocalDate.of(1989, FEBRUARY,17),
                    "eleri89@email.com",
                    DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()),
                    "Belfast",
                    true
            );

            repository.saveAll(
                    List.of(keksi, eleri)
            );
        };
    }
}

