package com.primestore.bl_product.service;

import com.primestore.bl_product.domain.Laptop;
import com.primestore.bl_product.domain.Product;
import com.primestore.bl_product.domain.Smartphone;
import com.primestore.bl_product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repo;

    public List<Laptop> getLaptops() {
        return repo.getLaptops();
    }

    public List<Smartphone> getSmartphones() {
        return repo.getSmartphones();
    }

    public List<Product> getProducts() {
        return repo.findAll();
    }

    public Product getProductById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found by id: " + id));
    }

    public Product createProduct(Product product) {
        return repo.save(product);
    }


    public boolean decrement(Long id, int count) {
        Product product = repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found by id: " + id));
        if (product.getCount() - count < 0) {
            throw new RuntimeException("Product count exceeds limit");
        }
        product.setCount(product.getCount() - count);
        repo.save(product);
        return true;
    }

    public boolean increment(Long id, int count) {
        Product product = repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found by id: " + id));
        if (count < 0) {
            throw new RuntimeException("increment count less than zero");
        }
        product.setCount(product.getCount() + count);
        repo.save(product);
        return true;
    }

}
