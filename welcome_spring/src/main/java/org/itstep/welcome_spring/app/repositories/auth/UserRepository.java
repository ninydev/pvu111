package org.itstep.welcome_spring.app.repositories.auth;

import org.itstep.welcome_spring.app.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
}
