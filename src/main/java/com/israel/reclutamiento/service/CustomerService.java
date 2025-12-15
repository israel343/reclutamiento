package com.israel.reclutamiento.service;

import com.israel.reclutamiento.dto.CreateCustomerRequest;
import com.israel.reclutamiento.dto.CustomerResponse;
import com.israel.reclutamiento.dto.MetricsResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CreateCustomerRequest request);
    List<CustomerResponse> listAllCustomers(int lifeExpectancyYears);
    MetricsResponse getMetrics();
    MetricsResponse getMetricsScalable();
}
