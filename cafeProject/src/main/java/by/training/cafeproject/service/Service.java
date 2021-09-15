package by.training.cafeproject.service;

import by.training.cafeproject.domain.Entity;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public interface Service<Type extends Entity> {
    void create(Type entity) throws ServiceException;

    Type read(Integer id) throws ServiceException;

    void update(Type entity) throws ServiceException;

    List<Type> read() throws ServiceException;

    void delete(Integer id) throws ServiceException;

    void delete(Type entity) throws ServiceException;
}
