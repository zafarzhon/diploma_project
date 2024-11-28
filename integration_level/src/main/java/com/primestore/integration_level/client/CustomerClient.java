package com.primestore.integration_level.client;


import com.primestore.integration_level.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Component
@FeignClient(name = "CustomerClient", url = "${app.feign.customer.url}", path = "${app.feign.customer.path}")
public interface CustomerClient {

    @PostMapping("/add")
    Customer save(@RequestBody Customer customer);

    @GetMapping("/by-login/{login}")
    Customer getCustomerByLogin(@PathVariable String login);
}
