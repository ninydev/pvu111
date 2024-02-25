package org.itstep.welcome_spring.app.repositories.auth;

import org.itstep.welcome_spring.app.models.auth.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long>
{
    boolean existsByEmail(String mail);

    Optional<Admin> findByEmail(String email);
}
