package org.itstep.welcome_spring.app.controllers.auth.admin;

import org.itstep.welcome_spring.app.requests.auth.admin.AdminLoginRequest;
import org.itstep.welcome_spring.app.responses.auth.admin.AdminLoginResponse;
import org.itstep.welcome_spring.app.services.auth.JwtAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/admin")
public class AdminLoginController {

    private final JwtAdminService authService;

    public AdminLoginController(JwtAdminService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequest loginRequest) {
        try {
            AdminLoginResponse response = authService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            System.out.println("Ex");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
