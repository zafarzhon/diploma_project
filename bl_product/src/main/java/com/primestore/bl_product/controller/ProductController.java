package com.primestore.bl_product.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.primestore.bl_product.domain.Product;
import com.primestore.bl_product.dto.LaptopDto;
import com.primestore.bl_product.dto.ProductDto;
import com.primestore.bl_product.dto.SmartphoneDto;
import com.primestore.bl_product.mapper.ProductMapper;
import com.primestore.bl_product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper;
    private final ObjectMapper objectMapper;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/only-laptops")
    public List<LaptopDto> getLaptops() {
        return mapper.laptopsToDtos(productService.getLaptops());
    }

    @GetMapping("/only-smartphones")
    public List<SmartphoneDto> getSmartphones() {
        return mapper.smartphonesToDtos(productService.getSmartphones());
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        return mapper.productsToDtos(productService.getProducts());
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
//        Product productById = productService.getProductById(id);
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//        if (productById instanceof Smartphone s) {
//            return objectMapper.writeValueAsString(s);
//        } else if (productById instanceof Laptop l) {
//            return objectMapper.writeValueAsString(l);
//        }
        return mapper.productToDto(productService.getProductById(id));
    }

    @GetMapping("/{id}/increment/{count}")
    public ResponseEntity<String> incrementProduct(@PathVariable Long id, @PathVariable int count) {
        productService.increment(id, count);
        return ResponseEntity.ok("Product incremented to " + count);
    }

    @GetMapping("/{id}/decrement/{count}")
    public ResponseEntity<String> decrementProduct(@PathVariable Long id, @PathVariable int count) {
        productService.decrement(id, count);
        return ResponseEntity.ok("Product decremented to " + count);
    }
}
