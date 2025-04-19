package com.example.securitylevels.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }

    /**
     * Этот бин настраивает цепочку фильтров безопасности:
     * - Отключает CSRF (не нужен для чистых REST API)
     * - Разрешает любые запросы без авторизации
     * - Отключает форму логина и BasicAuth
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1) Отключаем CSRF для stateless REST
                .csrf(csrf -> csrf.disable())
                // 2) Настраиваем авторизацию: все запросы разрешены
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                // 3) Отключаем любое сохранение сессии, форму логина, basic-auth
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());

        return http.build();
    }
}
