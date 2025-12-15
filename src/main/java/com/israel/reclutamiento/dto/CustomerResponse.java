package com.israel.reclutamiento.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private LocalDate birthDate;
    private LocalDate estimatedEventDate;
}
