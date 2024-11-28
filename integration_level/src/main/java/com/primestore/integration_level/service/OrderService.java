package com.primestore.integration_level.service;


import com.primestore.integration_level.client.CustomerClient;
import com.primestore.integration_level.client.OrderClient;
import com.primestore.integration_level.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderClient orderClient;
    private final CustomerClient customerClient;

    public Boolean doOrder(String cookies) {
        String customerLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        String cartJson = extractCartCookie(cookies);
        return orderClient.makeOrder(customerLogin, cartJson).getStatusCode().is2xxSuccessful();
    }

    public Customer getCustomer() {
        String customerLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        return customerClient.getCustomerByLogin(customerLogin);
    }


    private String extractCartCookie(String cookies) {
        for (String cookie : cookies.split(";")) {
            String[] parts = cookie.split("=", 2);
            if (parts.length == 2 && parts[0].trim().equals("cart")) {
                return parts[1].trim();
            }
        }
        throw new IllegalArgumentException("Кука 'cart' не найдена.");
    }

}
