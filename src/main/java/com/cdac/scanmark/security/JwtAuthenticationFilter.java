package com.cdac.scanmark.security ;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

import com.cdac.scanmark.repository.StudentRepository;
import com.cdac.scanmark.util.JwtUtil;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final StudentRepository studentRepository;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, StudentRepository studentRepository) {
        this.jwtUtil = jwtUtil;
        this.studentRepository = studentRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Skip the filter for /api/auth/login endpoint
        if (request.getRequestURI().equals("/api/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Long prn = Long.valueOf(jwtUtil.extractUsername(token));

                // Verify token and fetch user details
                studentRepository.findByPrn(prn).ifPresent(student -> {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            student, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });

            } catch (Exception e) {
                // Invalid token
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
