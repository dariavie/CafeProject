package by.training.cafeproject.service;

import by.training.cafeproject.domain.Ingredient;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public interface IngredientService extends Service {
    void save(Ingredient ingredient) throws ServiceException;

    Ingredient findById(Integer id) throws ServiceException;

    Ingredient findByTitle(String title) throws ServiceException;

    List<Ingredient> findAll() throws ServiceException;

    void delete(Integer id) throws ServiceException;

    void deleteByTitle(String title) throws ServiceException;
}
