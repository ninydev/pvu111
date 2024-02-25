package org.itstep.welcome_spring.app.seeds;


import org.itstep.welcome_spring.app.repositories.auth.AdminRepository;
import org.itstep.welcome_spring.app.models.auth.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeed implements CommandLineRunner {

    private final AdminRepository adminRepository;


    @Autowired
    public AdminSeed(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Проверяем, существует ли администратор с указанным email
        if (!adminRepository.existsByEmail("admin@admin.com")) {
            // Если не существует, создаем нового администратора
            Admin admin = new Admin();
            admin.setEmail("admin@admin.com");
            admin.setPassword("QweAsdZxc!23");
            // Сохраняем администратора в базе данных
            adminRepository.save(admin);
            System.out.println("Администратор admin@admin.com QweAsdZxc!23 создан");
        } else {
            // Если администратор с указанным email уже существует, вы можете выполнить здесь необходимые действия для обновления записи, если это требуется.
            System.out.println("Администратор с указанным email уже существует.");
        }
    }
}
