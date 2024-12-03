package com.cdac.scanmark.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // Bean for AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/**",
                                "/api/students/login",
                                "/api/coordinators/signup",
                                "/api/coordinators/login",
                                "/api/faculty/login",
                                "/api/coordinators/verify-otp",
                                "/api/coordinators/forgot-password",
                                "/api/coordinators/reset-password",
                                "/api/students/forgot-password",
                                "/api/students/reset-password",
                                "/api/faculty/forgot-password",
                                "/api/faculty/reset-password",
                                "/api/coordinators/add-student",
                                "/api/coordinators/add-faculty").permitAll()  // Allow auth and login endpoints
                        .requestMatchers("/api/coordinators/profile",
                                "/api/coordinators/search-student/{prn}",
                                "/api/coordinators/search-attendance-by-date/{date}",
                                "/api/coordinators/faculty-history/{facultyCode}").hasRole("COORDINATOR")  // Ensure only coordinators can access this endpoint
                        .requestMatchers("/api/faculty/profile").hasRole("FACULTY")  // Ensure only faculty can access this endpoint
                        .requestMatchers("/api/students/profile").hasRole("STUDENT")  // Ensure only students can access this endpoint
                        .anyRequest().authenticated()  // Secure all other endpoints
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // CORS configuration bean
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");  // Allow all origins (for development purposes)
            }
        };
    }
}
