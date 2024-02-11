package org.itstep.welcome_spring.app.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "entities")
public class EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private UUID id;
    private Long id;

    private String name;
    private int age;

}
