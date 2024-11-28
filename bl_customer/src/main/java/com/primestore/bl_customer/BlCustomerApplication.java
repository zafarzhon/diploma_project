package com.primestore.bl_customer;

import com.primestore.bl_customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
public class BlCustomerApplication {
    private final CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlCustomerApplication.class, args);
    }

//    @PostConstruct
//    private void init() {
//        Customer customer = Customer.builder().
//                login("user").
//                password("$2a$12$WMrDj.y0YVFIJOQ8CCxLUOXjzWGYP96DF.oQl0k/ifWEVQjS6SDem"). // password
//                balance(50000).
//                build();
//        customerRepository.save(customer);
//    }
}
