package com.israel.reclutamiento.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateCustomerRequest {
    @NotBlank(message = "firstName requerido")
    private String firstName;

    @NotBlank(message = "lastName requerido")
    private String lastName;

    @NotNull(message = "birthDate requerido")
    @Past(message = "birthDate debe ser una fecha pasada")
    private LocalDate birthDate;
}
