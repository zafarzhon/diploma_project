package com.primestore.bl_customer.repository;


import com.primestore.bl_customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByLogin(String customerName);
}
