package by.training.cafeproject.service;

import by.training.cafeproject.domain.Ingredient;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public interface IngredientService extends Service<Ingredient> {
    List<Ingredient> readByTitle(String search) throws ServiceException;

    void deleteByTitle(String title) throws ServiceException;
}
