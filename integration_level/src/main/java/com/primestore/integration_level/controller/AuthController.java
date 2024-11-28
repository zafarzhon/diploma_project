package com.primestore.integration_level.controller;


import com.primestore.integration_level.dto.AuthRequestDto;
import com.primestore.integration_level.dto.AuthResponseDto;
import com.primestore.integration_level.service.AuthService;
import com.primestore.integration_level.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final CustomerService customerService;

    @PostMapping("/log-in")
    public String auth(AuthRequestDto authRequestDto, HttpServletResponse response) {
        AuthResponseDto auth;
        try {
            auth = authService.auth(authRequestDto);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return "redirect:/";
        }

        ResponseCookie cookie = ResponseCookie.from("access_token", auth.getToken()).
                httpOnly(true).
                secure(true).
                path("/").
                maxAge(7 * 24 * 60 * 60).
                sameSite("Strict").
                build();
        response.addHeader("Set-Cookie", cookie.toString());
        return "redirect:/";
    }

    @GetMapping("/log-out")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("access_token", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        return "redirect:/";
    }

//    @PostMapping("/sign-up")
//    public String signup(@RequestBody Customer customer) {
//        customerService.save(customer);
//        AuthResponseDto auth;
//        try {
//            auth = authService.auth(customer);
//        } catch (RuntimeException e) {
//            System.err.println(e.getMessage());
//            return "redirect:/error";
//        }
//        return "redirect:/";
//    }
}
