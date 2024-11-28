package com.primestore.integration_level.client;


import com.primestore.integration_level.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */

@Component
@FeignClient(name = "OrderClient", url = "${app.feign.customer.url}", path = "/order")
public interface OrderClient {

    @PostMapping("/make")
    ResponseEntity<Customer> makeOrder(@RequestParam(name = "login") String login,
                                       @RequestParam(name = "cartJson") String cartJson);
}