package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Entity;

import java.util.List;

public interface Dao<Type extends Entity> {

    void create(Type entity) throws DaoException;

    Type read(Integer id) throws DaoException;

    void update(Type entity) throws DaoException;

    List<Type> read() throws DaoException;

    void delete(Integer id) throws DaoException;

    void delete(Type entity) throws DaoException;
}

