package com.israel.reclutamiento.controller;

import com.israel.reclutamiento.dto.CreateCustomerRequest;
import com.israel.reclutamiento.dto.CustomerResponse;
import com.israel.reclutamiento.dto.MetricsResponse;
import com.israel.reclutamiento.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Customers", description = "Operaciones sobre clientes")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    /**
     * Crear cliente
     */
    @Operation(summary = "Crear cliente", description = "Registra un nuevo cliente")
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CreateCustomerRequest request) {
        CustomerResponse created = service.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Listar clientes.
     * Parámetro opcional lifeExpectancy (años) para calcular la fecha estimada.
     */
    @Operation(summary = "Listar clientes", description = "Lista todos los clientes con fecha estimada")
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> listCustomers(
            @RequestParam(name = "lifeExpectancy", required = false) Integer lifeExpectancy) {
        int life = (lifeExpectancy == null) ? 80 : lifeExpectancy;
        List<CustomerResponse> list = service.listAllCustomers(life);
        return ResponseEntity.ok(list);
    }

    /**
     * Métricas: promedio y desviación estándar de edades
     */
    @Operation(summary = "Métricas", description = "Promedio y desviación estándar de edades")
    @GetMapping("/metrics")
    public ResponseEntity<MetricsResponse> getMetrics() {
        MetricsResponse metrics = service.getMetrics();
        return ResponseEntity.ok(metrics);
    }
    /**
     * Métricas: promedio y desviación estándar de edades , a nivel de base de datos
     */
    @Operation(summary = "Métricas", description = "Promedio y desviación estándar de edades")
    @GetMapping("/metrics/scalable")
    public ResponseEntity<MetricsResponse> getMetricsScalable() {
        MetricsResponse metrics = service.getMetricsScalable();
        return ResponseEntity.ok(metrics);
    }
}