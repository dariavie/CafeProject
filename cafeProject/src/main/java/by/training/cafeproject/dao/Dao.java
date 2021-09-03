package by.training.cafeproject.dao;

import by.training.cafeproject.entity.Entity;

import java.util.List;

public interface Dao<Type extends Entity> {
    Integer create(Type entity);

    Type read(Integer id);

    void update(Type entity);

    List<Type> read();

    void delete(Integer id);

    void delete(Type entity);
}

