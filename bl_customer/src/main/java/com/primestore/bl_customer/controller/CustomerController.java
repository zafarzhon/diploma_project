package com.primestore.bl_customer.controller;


import com.primestore.bl_customer.domain.Customer;
import com.primestore.bl_customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/add")
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping("{id}")
    public Customer getCustomerById(@PathVariable long id) {
        return customerService.findById(id);
    }

    @GetMapping("/list")
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/by-login/{login}")
    public Customer getCustomerByLogin(@PathVariable String login) {
        Customer customerByLogin = customerService.getCustomerByLogin(login);
        return customerService.getCustomerByLogin(login);
    }
}
