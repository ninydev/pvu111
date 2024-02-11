package org.itstep.welcome_spring.app.repositories;

import org.itstep.welcome_spring.app.models.EntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Реализация данного интерфейса создаст мне все методы для
 * построения SQL запросов к сущности
 * И создан он будет автоматически
 */
public interface EntityRepository extends JpaRepository<EntityModel, Long>
{
}
