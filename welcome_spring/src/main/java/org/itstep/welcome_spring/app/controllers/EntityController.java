package org.itstep.welcome_spring.app.controllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.itstep.welcome_spring.app.models.EntityModel;
import org.itstep.welcome_spring.app.repositories.EntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/entity")
/**
 * Данный контроллер должен реализовать CRUD для сущности
 */
public class EntityController {

    private EntityRepository entityRepository;

    /**
     * С помощью DI предоставим интерфейс для досупа к базе
     * @param entityRepository
     */
    public  EntityController(EntityRepository entityRepository){
        this.entityRepository = entityRepository;
    }


    /**
     * Создание новой сущности
     * @param newEntity
     * @return
     */
    @PostMapping("")
    public ResponseEntity<EntityModel> create(@RequestBody EntityModel newEntity) {

        // Ссылка (указатель) на вновь созданный экземпляр сущности
        EntityModel createdEntity = entityRepository.save(newEntity);

        // Ссылка (URI) на запрос к этой сущности
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdEntity.getId()).toUri();

        // Вернуть мне нужно статус - что создалось и что создалось
        return ResponseEntity.created(location).body(createdEntity);
    }




}
