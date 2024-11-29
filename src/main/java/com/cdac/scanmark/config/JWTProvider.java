package com.cdac.scanmark.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    // Generate JWT token
    // Generate JWT token using Authentication object
    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())  // Get the username (prn) from the authentication
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // Extract username from token
    public String getUsernameFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    // Validate token
    public boolean validateToken(String token, String username) {
        return username.equals(getUsernameFromToken(token)) && !isTokenExpired(token);
    }

    // Check if token is expired
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract claims from token
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // Extract expiration date from token
    private Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }
}
