package org.example.entities.repositories;

import org.example.entities.CRUDInterface;
import org.example.entities.users.User;

import java.util.ArrayList;
import java.util.UUID;

public class UserRepository implements CRUDInterface
{
    ArrayList<User> users = new ArrayList<>();

    @Override
    public void create() {
        // Создается новая запись
    }

    @Override
    public void readAll() {
        // Возвращаю всю коллекию
    }

    @Override
    public void read(UUID id) {

    }

    @Override
    public void update(UUID id, Object entity) {

    }

    @Override
    public void delete(UUID id) {

    }
}
