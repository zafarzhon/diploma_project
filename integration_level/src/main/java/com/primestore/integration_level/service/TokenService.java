package com.primestore.integration_level.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.List;

/**
 * @author zafarzhon
 * @link <a href="https://github.com/zafarzhon">github</a>
 */
@Service
public class TokenService {
    @Value("${app.jwt.signing-key}")
    private String jwtSigningKey;
    private SecretKey secretKey;

    public String createToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        System.out.println();
        return Jwts.builder().
                claim("username", username).
                claim("role", "ROLE_CUSTOMER").
                signWith(secretKey, SignatureAlgorithm.HS256).
                compact();
    }

    @PostConstruct
    private void getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isValidToken(String token) {
        try {
            Claims claims = Jwts.parser().
                    setSigningKey(secretKey).
                    build().
                    parseClaimsJws(token).
                    getPayload();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication parseToken(String token) {
        Claims claims = Jwts.parser().
                setSigningKey(secretKey).
                build().
                parseClaimsJws(token).
                getPayload();
        String username = claims.get("username", String.class);
        String role = claims.get("role", String.class);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
                List.of(new SimpleGrantedAuthority(role)));
        return authentication;
    }
}
