package com.primestore.integration_level.filter;


import com.primestore.integration_level.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getCookies() != null) {
            Arrays.stream(request.getCookies()).
                    filter(cookie -> cookie.getName().equals("access_token")).
                    findFirst().
                    ifPresent(cookie -> {
                        String token = cookie.getValue();
                        if (tokenService.isValidToken(token)) {
                            Authentication auth = tokenService.parseToken(token);
                            SecurityContextHolder.getContext().setAuthentication(auth);
                        }
                    });
        }
        filterChain.doFilter(request, response);
    }
}
