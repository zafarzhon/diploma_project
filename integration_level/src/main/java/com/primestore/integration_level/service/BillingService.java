package com.primestore.integration_level.service;

import com.primestore.integration_level.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Service
@RequiredArgsConstructor
public class BillingService {
    private final CustomerService customerService;

    public void addMoney(Integer amount, Model model) {
        Customer currentCustomer = (Customer) model.getAttribute("currentCustomer");
        assert currentCustomer != null;
        currentCustomer.setBalance(currentCustomer.getBalance() + amount);
        customerService.save(currentCustomer);
    }
}
