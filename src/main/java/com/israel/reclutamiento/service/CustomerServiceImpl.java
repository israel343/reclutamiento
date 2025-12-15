package com.israel.reclutamiento.service;

import com.israel.reclutamiento.dto.CreateCustomerRequest;
import com.israel.reclutamiento.dto.CustomerResponse;
import com.israel.reclutamiento.dto.MetricsResponse;
import com.israel.reclutamiento.entity.Customer;
import com.israel.reclutamiento.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;
    private static final int DEFAULT_LIFE_EXPECTANCY = 80;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest request) {

        Customer customer = new Customer(
                request.getFirstName(),
                request.getLastName(),
                request.getBirthDate()
        );

        Customer saved = repo.save(customer);

        return mapToResponse(saved, DEFAULT_LIFE_EXPECTANCY);
    }

    @Override
    public List<CustomerResponse> listAllCustomers(int lifeExpectancyYears) {

        int life = lifeExpectancyYears > 0
                ? lifeExpectancyYears
                : DEFAULT_LIFE_EXPECTANCY;

        return repo.findAll()
                .stream()
                .map(c -> mapToResponse(c, life))
                .toList();
    }

    @Override
    public MetricsResponse getMetrics() {

        List<Customer> customers = repo.findAll();
        int n = customers.size();

        if (n == 0) {
            return new MetricsResponse(0, 0, 0);
        }

        double average = customers.stream()
                .mapToInt(Customer::getAge)
                .average()
                .orElse(0);

        double variance = customers.stream()
                .mapToDouble(c -> Math.pow(c.getAge() - average, 2))
                .sum() / n;

        double stdDev = Math.sqrt(variance);

        return new MetricsResponse(round(average), round(stdDev), n);
    }

    @Override
    public MetricsResponse getMetricsScalable() {

        List<Object[]> resultList = repo.getCustomerMetrics();

        Object[] row = resultList.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No hay datos para métricas"));

        double averageAge = row[0] != null
                ? ((Number) row[0]).doubleValue()
                : 0.0;

        double stdDevAge = row[1] != null
                ? ((Number) row[1]).doubleValue()
                : 0.0;

        long totalCustomers = row[2] != null
                ? ((Number) row[2]).longValue()
                : 0L;

        return new MetricsResponse(
                averageAge,
                stdDevAge,
                totalCustomers
        );

    }

    private CustomerResponse mapToResponse(Customer c, int life) {

        CustomerResponse r = new CustomerResponse();
        r.setId(c.getId());
        r.setFirstName(c.getFirstName());
        r.setLastName(c.getLastName());
        r.setBirthDate(c.getBirthDate());
        r.setAge(c.getAge());
        r.setEstimatedEventDate(c.getBirthDate().plusYears(life));
        return r;
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }
}

