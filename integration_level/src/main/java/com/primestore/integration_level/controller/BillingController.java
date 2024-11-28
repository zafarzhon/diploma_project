package com.primestore.integration_level.controller;

import com.primestore.integration_level.dto.PaymentDto;
import com.primestore.integration_level.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class BillingController {
    private final BillingService billingService;

    @GetMapping("/add-money")
    public String addMoney(Model model) {
        model.addAttribute("paymentDto", new PaymentDto());
        return "add_money";
    }

    @PostMapping("/add-money")
    public String addMoney(@ModelAttribute("paymentDto") PaymentDto paymentDto, Model model) {
        //request another service
        // if ok to change balance
        boolean ok = true;
        if (ok) {
            billingService.addMoney(paymentDto.getAmount(), model);
            return "redirect:/";
        } else {
            return "redirect:/fail";
        }

    }
}
