package com.learning.covidcasestracker.statistics;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CovidCaseStatistics {
    private long deactivated;
    private long recovered;
    private long deceased;
}
