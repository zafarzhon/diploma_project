package com.primestore.bl_product.repository;

import com.primestore.bl_product.domain.Laptop;
import com.primestore.bl_product.domain.Product;
import com.primestore.bl_product.domain.Smartphone;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {"battery", "cpu", "display", "memory"})
    @Query("SELECT p FROM Smartphone p")
    List<Smartphone> getSmartphones();

    @EntityGraph(attributePaths = {"battery", "cpu", "display", "memory"})
    @Query("SELECT p FROM Laptop p")
    List<Laptop> getLaptops();


}
