package com.itstep.myrestapp.repositories;

public interface RepositoryInterface<T> {

    /**
     * Create
     * @return
     */
    T create(T newModel);


    T createModel();

}
