package org.itstep.welcome_spring.app.services.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.itstep.welcome_spring.app.models.auth.Admin;
import org.itstep.welcome_spring.app.repositories.auth.AdminRepository;
import org.itstep.welcome_spring.app.requests.auth.admin.AdminLoginRequest;
import org.itstep.welcome_spring.app.responses.auth.admin.AdminLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtAdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public JwtAdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public AdminLoginResponse login(AdminLoginRequest loginRequest) {
        // Находим администратора по email
        Admin admin = adminRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Администратор с указанным email не найден"));

        // Проверяем, что пароль совпадает
        if (!passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {
            throw new IllegalArgumentException("Неверный пароль");
        }

        // Создаем JWT токен
        String accessToken = generateAccessToken(admin.getId(), admin.getEmail());

        // Возвращаем данные об администраторе и JWT токен
        return new AdminLoginResponse(admin.getId(), admin.getEmail(), accessToken);
    }

    @Value("${jwt.admin.secret}")
    private String secretKey;

    public Admin parseAccessToken(String accessToken) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(secretKey);
            SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");

            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken);

            String adminIdString = claims.getBody().getSubject();
            Long adminId = Long.parseLong(adminIdString);
            String email = (String) claims.getBody().get("email");

            // Создаем объект Admin
            Admin admin = new Admin();
            admin.setId(adminId);
            admin.setEmail(email);

            return admin;
        } catch (Exception e) {
            // В случае ошибки при парсинге токена или недействительного токена возвращаем null или бросаем исключение
            return null;
        }
    }

    // Метод для генерации JWT токена
    private String generateAccessToken(Long adminId, String email) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder()
                .setSubject(adminId.toString())
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Устанавливаем срок действия токена (1 час)
                .signWith(key) // Здесь нужно использовать ваш секретный ключ
                .compact();
    }
}
