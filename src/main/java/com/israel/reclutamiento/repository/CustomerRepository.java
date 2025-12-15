package com.israel.reclutamiento.repository;

import com.israel.reclutamiento.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "CALL sp_customer_age_metrics()", nativeQuery = true)
    List<Object[]> getCustomerMetrics();
}