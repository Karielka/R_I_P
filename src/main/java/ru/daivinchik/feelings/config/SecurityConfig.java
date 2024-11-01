package ru.daivinchik.feelings.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class SecurityConfig {

    // Этот метод предоставляет экземпляр BCryptPasswordEncoder как Bean, чтобы он был доступен для инъекций
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .anyRequest().permitAll() // Разрешаем доступ ко всем запросам без авторизации
                )
                .csrf(csrf -> csrf.disable()); // Новый способ отключения CSRF

        return http.build();
    }
}
