package com.primestore.bl_customer.controller;


import com.primestore.bl_customer.domain.Customer;
import com.primestore.bl_customer.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/make")
    public ResponseEntity<Customer> makeOrder(@RequestParam(name = "login") String login,
                                              @RequestParam(name = "cartJson") String cartJson) {
        return ResponseEntity.ok(orderService.makeOrder(login, cartJson));
    }
}
