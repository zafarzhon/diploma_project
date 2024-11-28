package com.primestore.integration_level.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {


    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/error")
    public String error() {
        return "";
    }
}
