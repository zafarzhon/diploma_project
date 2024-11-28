package com.primestore.bl_customer.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@FeignClient(name = "ProductClient", url = "127.0.0.1:8081/products")
public interface ProductClient {

    @GetMapping("/{id}")
    String getProduct(@PathVariable Long id);

    @GetMapping("/{id}/increment/{count}")
    ResponseEntity<String> incrementProduct(@PathVariable Long id, @PathVariable int count);

    @GetMapping("/{id}/decrement/{count}")
    ResponseEntity<String> decrementProduct(@PathVariable Long id, @PathVariable int count);
}
