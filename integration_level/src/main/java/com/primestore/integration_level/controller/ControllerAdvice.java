package com.primestore.integration_level.controller;

import com.primestore.integration_level.dto.Customer;
import com.primestore.integration_level.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@org.springframework.web.bind.annotation.ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {
    private final CustomerService customerService;

    @ModelAttribute("currentCustomer")
    public Customer getCurrentCustomer() {
        if (SecurityContextHolder.getContext().getAuthentication().getName() != null &&
                !SecurityContextHolder.getContext().getAuthentication().getName().
                        equalsIgnoreCase("anonymousUser")) {
            String login = SecurityContextHolder.getContext().getAuthentication().getName();
            return customerService.findByUsername(login);
        }
        return null;
    }
}
