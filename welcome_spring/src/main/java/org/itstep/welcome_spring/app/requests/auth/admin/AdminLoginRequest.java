package org.itstep.welcome_spring.app.requests.auth.admin;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "запрос на вход администратору")
public class AdminLoginRequest {
    @Schema(description = "Email администратора", example = "admin@admin.com")
    @Size(min = 1, max = 100)
    private String email;

    @Schema(description = "Пароль администратора", example = "password")
    @Size(min = 8, max = 255)
    private String password;
}
