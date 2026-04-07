package com.se.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Cấu hình Spring Security và CORS cho phép truyền dữ liệu qua mạng LAN
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Cấu hình Security Filter Chain
     * - Cho phép tất cả các requests (phù hợp cho môi trường development/LAN)
     * - Tắt CSRF protection (vì dùng REST API stateless)
     * - Bật CORS với cấu hình tùy chỉnh
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Tắt CSRF vì REST API không dùng session
            .csrf(csrf -> csrf.disable())
            
            // Cấu hình CORS
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // Cấu hình authorization
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()  // Cho phép tất cả requests (phù hợp LAN)
            )
            
            // Cấu hình session management - STATELESS cho REST API
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }

    /**
     * Cấu hình CORS để cho phép truy cập từ các địa chỉ trong mạng LAN
     * Cấu hình này cho phép:
     * - Tất cả origins trong mạng LAN (192.168.x.x, 10.x.x.x, 172.16-31.x.x)
     * - Tất cả HTTP methods (GET, POST, PUT, DELETE, etc.)
     * - Tất cả headers
     * - Credentials (cookies, authorization headers)
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Cho phép tất cả origins (trong production nên chỉ định cụ thể)
        // Ví dụ: configuration.setAllowedOrigins(Arrays.asList("http://192.168.1.100:3000", "http://192.168.1.101:3000"));
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        
        // Hoặc chỉ cho phép các địa chỉ trong mạng LAN cụ thể:
        // configuration.setAllowedOriginPatterns(Arrays.asList(
        //     "http://192.168.*.*:*",
        //     "http://10.*.*.*:*",
        //     "http://172.16.*.*:*",
        //     "http://172.17.*.*:*",
        //     "http://172.18.*.*:*",
        //     "http://172.19.*.*:*",
        //     "http://172.20.*.*:*",
        //     "http://172.21.*.*:*",
        //     "http://172.22.*.*:*",
        //     "http://172.23.*.*:*",
        //     "http://172.24.*.*:*",
        //     "http://172.25.*.*:*",
        //     "http://172.26.*.*:*",
        //     "http://172.27.*.*:*",
        //     "http://172.28.*.*:*",
        //     "http://172.29.*.*:*",
        //     "http://172.30.*.*:*",
        //     "http://172.31.*.*:*",
        //     "http://localhost:*"
        // ));
        
        // Cho phép tất cả HTTP methods
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        
        // Cho phép tất cả headers
        configuration.setAllowedHeaders(Arrays.asList("*"));
        
        // Cho phép gửi credentials (cookies, authorization headers)
        configuration.setAllowCredentials(true);
        
        // Thời gian cache preflight request (3600 giây = 1 giờ)
        configuration.setMaxAge(3600L);
        
        // Các headers được phép expose cho client
        configuration.setExposedHeaders(Arrays.asList(
            "Authorization", 
            "Content-Type", 
            "X-Total-Count"
        ));
        
        // Áp dụng cấu hình CORS cho tất cả endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}

