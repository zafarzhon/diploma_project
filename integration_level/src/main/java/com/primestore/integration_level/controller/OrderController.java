package com.primestore.integration_level.controller;


import com.primestore.integration_level.dto.Customer;
import com.primestore.integration_level.dto.order.Order;
import com.primestore.integration_level.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/make")
    public String doOrder(@RequestHeader(value = "Cookie", required = false) String cookies, HttpServletResponse response) {
        Boolean b = orderService.doOrder(cookies);
        if (b) {
            ResponseCookie cookie = ResponseCookie.from("cart", "")
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(0)
                    .sameSite("Strict")
                    .build();
            response.addHeader("Set-Cookie", cookie.toString());
            return "redirect:/order/history";
        } else {
            return "redirect:/order/fail";
        }
    }

    @GetMapping("/history")
    public String doHistory(Model model) {
        Customer currentCustomer = (Customer) model.getAttribute("currentCustomer");
        assert currentCustomer != null;
        model.addAttribute("orders", currentCustomer.getOrders().stream().sorted(Comparator.comparing(Order::getCreatedAt).reversed()).toList());
        return "my_orders";
    }
}
