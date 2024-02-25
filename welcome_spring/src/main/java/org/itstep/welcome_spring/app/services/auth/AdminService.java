package org.itstep.welcome_spring.app.services.auth;

import org.itstep.welcome_spring.app.repositories.auth.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService
{
//    @Autowired
//    private AdminRepository adminRepository;

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
}
