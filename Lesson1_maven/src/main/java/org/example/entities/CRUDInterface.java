package org.example.entities;

import java.util.UUID;

public interface CRUDInterface {
    void create();
    void readAll();
    void read(UUID id);

    void update(UUID id, Object entity);
    void delete (UUID id);

}
