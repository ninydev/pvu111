package org.itstep.welcome_spring.app.config.auth;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.itstep.welcome_spring.app.models.auth.Admin;
import org.itstep.welcome_spring.app.services.auth.JwtAdminService;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Order(Ordered.LOWEST_PRECEDENCE)
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";

    private final JwtAdminService jwtAdminService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Получаем токен из заголовка
        var authHeader = request.getHeader(HEADER_NAME);
        // Если ключа в заголовке нет вообще - значит пользователь не авторизирован
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Обрезаем префикс и получаем имя пользователя из токена
        var jwt = authHeader.substring(BEARER_PREFIX.length());


        Admin admin = jwtAdminService.parseAccessToken(jwt);
        // тут у меня возможно 2 варианта - или ключ выдан админами, или ключ выдан пользователям
        // в любом случае - я обращаюсь к сервисам, которые гененрируют ключи
        // Сначала я пытаюсь обратиться к сервису админов, даю ему ключ - и получаю (или не получаю) администратора

        // Значит из ключа я получил админа
        if (admin != null) {
            System.out.println(" Судя по восстановленной информации из ключа - к нам пришел админ ");
        }



//        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userService
//                    .userDetailsService()
//                    .loadUserByUsername(username);
//
//            // Если токен валиден, то аутентифицируем пользователя
//            if (jwtService.isTokenValid(jwt, userDetails)) {
//                SecurityContext context = SecurityContextHolder.createEmptyContext();
//
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                context.setAuthentication(authToken);
//                SecurityContextHolder.setContext(context);
//            }
//        }
        filterChain.doFilter(request, response);
    }
}