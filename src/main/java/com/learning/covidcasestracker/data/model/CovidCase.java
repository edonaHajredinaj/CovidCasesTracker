package com.learning.covidcasestracker.data.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "cases")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CovidCase {

    @Id
    @SequenceGenerator(
            name = "covidcases_sequence",
            sequenceName = "covidcases_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "covidcases_sequence"
    )
    private Integer PatientID;
    private String fullName;
    private LocalDate birthday;
    @Transient
    private Integer age;
    private String email;
    private String casePeriod;
    private String city;
    private boolean underlyingDisease;
    private boolean deactivated;
    private boolean recovered;
    private boolean deceased;

    public Integer getAge() {
        return Period.between(this.birthday, LocalDate.now()).getYears();
    }
}
