package org.itstep.welcome_spring.app.models.auth;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
@Data
public class BaseUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // Уникальность почты
    private String email;

    private String password;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    // Дополнительная проверка на корректность формата email
    @PrePersist
    @PreUpdate
    private void validateEmail() {
        if (email != null && !email.matches("^[\\w-_.+]*[\\w-_.]@[\\w]+[.]([\\w]+[.])*[\\w]{2,}$")) {
            throw new IllegalArgumentException("Некорректный формат email");
        }
    }
}
