package com.primestore.integration_level.client;

import com.primestore.integration_level.dto.Laptop;
import com.primestore.integration_level.dto.ProductDto;
import com.primestore.integration_level.dto.Smartphone;
import com.primestore.integration_level.dto.order.ProductInfoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */

@FeignClient(name = "productClient", url = "${app.feign.product.url}", path = "${app.feign.product.path}")
public interface ProductClient {

    @PostMapping
    ProductInfoOrder createProduct(@RequestBody ProductInfoOrder productInfoOrder);

    @GetMapping("/only-laptops")
    List<Laptop> getLaptops();

    @GetMapping("/only-smartphones")
    List<Smartphone> getSmartphones();

    @GetMapping
    List<ProductDto> getProducts();

    @GetMapping("/{id}")
    String getProduct(@PathVariable Long id);

    @GetMapping("/{id}/increment/{count}")
    ResponseEntity<String> incrementProduct(@PathVariable Long id, @PathVariable int count);

    @GetMapping("/{id}/decrement/{count}")
    ResponseEntity<String> decrementProduct(@PathVariable Long id, @PathVariable int count);
}
