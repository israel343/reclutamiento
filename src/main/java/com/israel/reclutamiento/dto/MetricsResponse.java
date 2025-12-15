package com.israel.reclutamiento.dto;

import lombok.Data;

@Data
public class MetricsResponse {
    private double averageAge;
    private double stdDevAge;
    private long totalCustomers;

    public MetricsResponse(double averageAge, double stdDevAge, long totalCustomers) {
        this.averageAge = averageAge;
        this.stdDevAge = stdDevAge;
        this.totalCustomers = totalCustomers;
    }
}