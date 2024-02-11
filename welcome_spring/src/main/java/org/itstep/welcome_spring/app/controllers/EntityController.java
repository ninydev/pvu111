package org.itstep.welcome_spring.app.controllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.itstep.welcome_spring.app.models.EntityModel;
import org.itstep.welcome_spring.app.repositories.EntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entity")
/**
 * Данный контроллер должен реализовать CRUD для сущности
 */
public class EntityController {

    private static final Logger logger = LoggerFactory.getLogger(EntityController.class);

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

        logger.info("Получена сущность: {}", newEntity);

        // Ссылка (указатель) на вновь созданный экземпляр сущности
        EntityModel createdEntity = entityRepository.save(newEntity);

        logger.info("Создана сущность: {}", newEntity);

        // Ссылка (URI) на запрос к этой сущности
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdEntity.getId()).toUri();

        // Вернуть мне нужно статус - что создалось и что создалось
        return ResponseEntity.created(location).body(createdEntity);
    }

//    @GetMapping("")
//    public ResponseEntity<Page<EntityModel>> readAll(Pageable pageable) {
//        Page<EntityModel> page = (Page<EntityModel>) entityRepository.findAll(pageable);
//
//        if (page.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//
//        return ResponseEntity.ok(page);
//    }

    /**
     * Получение всех сущностей
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<EntityModel>> readAll() {
        List<EntityModel> result = new ArrayList<>();
        entityRepository.findAll().forEach(result::add);

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }


    /**
     * Получение одной сущности
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel> readById(@PathVariable Long id) {

        Optional<EntityModel> result = entityRepository.findById(id);

        return result.map(
          entityModel -> ResponseEntity.ok(entityModel))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Обновление сущности
     * @param entity
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel> update(@RequestBody EntityModel entity, @PathVariable Long id) {
        Optional<EntityModel> promise = entityRepository.findById(id);
        if(promise.isPresent()) {
            EntityModel updateEntity = promise.get();
            updateEntity.setName(entity.getName());
            updateEntity.setAge(entity.getAge());
            entityRepository.save(updateEntity);
            return ResponseEntity.ok(updateEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаление сущности
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<EntityModel> promise = entityRepository.findById(id);
        if(promise.isPresent()) {
            entityRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
