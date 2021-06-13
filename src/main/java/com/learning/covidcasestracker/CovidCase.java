package com.learning.covidcasestracker;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
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

    //no need for age to be a column in the database,
    // age is calculated

    protected CovidCase() {
    }

    protected CovidCase(String fullName,
                        LocalDate birthday,
                        String email,
                        String casePeriod,
                        String city,
                        boolean underlyingDisease) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.email = email;
        this.casePeriod = casePeriod;
        this.city = city;
        this.underlyingDisease = underlyingDisease;
    }

    public CovidCase(Integer patientID,
                     String fullName,
                     LocalDate birthday,
                     String email,
                     String casePeriod,
                     String city,
                     boolean underlyingDisease) {
        PatientID = patientID;
        this.fullName = fullName;
        this.birthday = birthday;
        this.email = email;
        this.casePeriod = casePeriod;
        this.city = city;
        this.underlyingDisease = underlyingDisease;
    }

    public Integer getPatientID() {
        return PatientID;
    }

    public void setPatientID(Integer patientID) {
        PatientID = patientID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return Period.between(this.birthday, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCasePeriod() {
        return casePeriod;
    }

    public void setCasePeriod(String casePeriod) {
        this.casePeriod = casePeriod;
    }

    public boolean isUnderlyingDisease() {
        return underlyingDisease;
    }

    public void setUnderlyingDisease(boolean underlyingDisease) {
        this.underlyingDisease = underlyingDisease;
    }

    @Override
    public String toString() {
        return "CovidCase{" +
                "PatientID=" + PatientID +
                ", fullName='" + fullName + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", casePeriod='" + casePeriod + '\'' +
                ", city='" + city + '\'' +
                ", underlyingDisease=" + underlyingDisease +
                '}';
    }
}
