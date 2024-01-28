package org.example.entities.repositories;

import org.example.entities.CRUDInterface;

import java.util.UUID;

public class UserCacheRepository implements CRUDInterface {

    UserRepository userRepository =new UserRepository();

    @Override
    public void create() {
        // передаю в базовый репозиторий задачу создать пользователя
        userRepository.create();

    }

    @Override
    public void readAll() {
//        // Возвращаю коллекцию
//        userRepository.readAll();

        // Я могу работать с кешем - то есть
        // Если у меня есть подготовленный ответ - отдать его
        // если нет - получить его - сохранить в кеш и отдать

    }

    @Override
    public void read(UUID id) {
        userRepository.read(id);
    }

    @Override
    public void update(UUID id, Object entity) {
        userRepository.update(id, entity);
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(id);
    }
}
