package com.primestore.integration_level.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.primestore.integration_level.client.ProductClient;
import com.primestore.integration_level.dto.Laptop;
import com.primestore.integration_level.dto.Smartphone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ProductController {
    private final ProductClient client;
    private final ObjectMapper objectMapper;


    @GetMapping("/only-smartphones")
    public String smartphones(Model model) {

        List<Smartphone> all = client.getSmartphones();
        model.addAttribute("products", all);
        return "smartphones";
    }

    @GetMapping("/only-laptops")
    public String laptops(Model model) {
        List<Laptop> all = client.getLaptops();
        model.addAttribute("products", all);
        return "laptops";
    }

    @GetMapping("/products/{id}")
    public String smartphoneById(@PathVariable long id, Model model) throws JsonProcessingException {
        String product = client.getProduct(id);
        if (product.contains("Смартфон")) {
            Smartphone smartphone = objectMapper.readValue(product, Smartphone.class);
            model.addAttribute("product", smartphone);
            return "smartphone";
        } else if (product.contains("Ноутбук")) {
            Laptop laptop = objectMapper.readValue(product, Laptop.class);
            model.addAttribute("product", laptop);
            return "laptop";
        }
        return "error";
    }


}
