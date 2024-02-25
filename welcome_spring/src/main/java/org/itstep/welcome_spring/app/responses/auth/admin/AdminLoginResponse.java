package org.itstep.welcome_spring.app.responses.auth.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Ключ админа")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminLoginResponse {
    @Schema(description = "ID администратора")
    private Long id;

    @Schema(description = "Email администратора")
    private String email;

    @Schema(description = "JWT токен доступа")
    private String accessToken;
}
