package org.itstep.welcome_spring.app.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                // Поскольку я планирую строить приложение на JS фреймворке - этот тип защиты
                // для меня не актуален. Я не строю формы на стороне сервера - и могу его отключить
                .csrf(AbstractHttpConfigurer::disable)

                // Своего рода отключение CORS (разрешение запросов со всех доменов)
                // Открывать межсайтовые возможности запросов - плохой способ - можно подвергнуться атаке хакеров
                // По умолчанию он запрещен (и открыть его можно только на период разработки для временного удобства)
//                .cors(cors -> cors.configurationSource(request -> {
//                    var corsConfiguration = new CorsConfiguration();
//                    corsConfiguration.setAllowedOriginPatterns(List.of("*"));
//                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//                    corsConfiguration.setAllowedHeaders(List.of("*"));
//                    corsConfiguration.setAllowCredentials(true);
//                    return corsConfiguration;
//                }))
                // Настройка доступа к конечным точкам
                .authorizeHttpRequests(request -> request
                        // Можно указать конкретный путь, * - 1 уровень вложенности, ** - любое количество уровней вложенности

                        // Отрыть маршруты для авторизации пользователей
                        .requestMatchers("/api/auth/**").permitAll()

                        // Открыть маршруты для документации
                        .requestMatchers("/docs/**").permitAll()
                        .requestMatchers("/swagger-ui-custom.html").permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-resources/*", "/v3/api-docs/**").permitAll()

                        // Закрыть все остальные маршруты
                        .anyRequest().authenticated()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                // .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, JwtAuthenticationFilter.class)
        ;
        return http.build();
    }

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
////        authProvider.setUserDetailsService(userService.userDetailsService());
////        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
//            throws Exception {
//        return config.getAuthenticationManager();
//    }
}
