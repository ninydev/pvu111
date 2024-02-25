package org.itstep.welcome_spring.app.services.auth;

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

import java.security.Key;
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

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // Метод для генерации JWT токена
    private String generateAccessToken(Long adminId, String email) {
        return Jwts.builder()
                .setSubject(adminId.toString())
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Устанавливаем срок действия токена (1 час)
                .signWith(this.key) // Здесь нужно использовать ваш секретный ключ
                .compact();
    }
}
