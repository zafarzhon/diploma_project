package com.primestore.integration_level.configuration;


import com.primestore.integration_level.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        return http.
                cors(AbstractHttpConfigurer::disable).
                csrf(AbstractHttpConfigurer::disable).
                sessionManagement(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(cust -> {
                    cust.requestMatchers("/order/*/**").authenticated();
                    cust.requestMatchers("/add-money", "/log-out").authenticated();
                    cust.requestMatchers("/**").permitAll();
                }).
                addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).
                build();
    }
}
