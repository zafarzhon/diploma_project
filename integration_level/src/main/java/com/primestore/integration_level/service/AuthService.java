package com.primestore.integration_level.service;


import com.primestore.integration_level.dto.AuthRequestDto;
import com.primestore.integration_level.dto.AuthResponseDto;
import com.primestore.integration_level.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthResponseDto auth(AuthRequestDto authRequestDto) {
        UserDetails userDetails = null;
        try {
            userDetails = customerService.userDetailsService().loadUserByUsername(authRequestDto.getLogin());
        } catch (RuntimeException e) {
            Customer customer = new Customer();
            customer.setLogin(authRequestDto.getLogin());
            customer.setPassword(authRequestDto.getPassword());
            userDetails = customerService.save(customer);
        }

        if (!passwordEncoder.matches(authRequestDto.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return new AuthResponseDto(tokenService.createToken(userDetails));
    }

}
