package com.learning.covidcasestracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Calendar.APRIL;
import static java.util.Calendar.FEBRUARY;

@Configuration
public class CovidCaseConfig {

    @Bean
    CommandLineRunner commandLineRunner(CovidCaseRepository repository) {
        return args -> {
            CovidCase keksi = CovidCase.builder()
                    .fullName("Filan Keksi")
                    .birthday(LocalDate.of(1999, APRIL, 5))
                    .email("filankeksi@email.com")
                    .casePeriod(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()))
                    .city("Prizren")
                    .underlyingDisease(false)
                    .deactivated(false)
                    .recovered(false)
                    .deceased(false)
                    .build();

            CovidCase eleri = CovidCase.builder()
                    .fullName("William Eleri")
                    .birthday(LocalDate.of(1989, FEBRUARY, 17))
                    .email("eleri89@email.com")
                    .casePeriod(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()))
                    .city("Belfast")
                    .underlyingDisease(false)
                    .deactivated(false)
                    .recovered(false)
                    .deceased(false)
                    .build();

            repository.saveAll(
                    List.of(keksi, eleri)
            );
        };
    }
}

