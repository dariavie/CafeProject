package by.training.cafeproject.service;

import by.training.cafeproject.domain.FoodIngredient;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public interface FoodIngredientService extends Service<FoodIngredient> {
    List<FoodIngredient> readByFoodId(Integer foodId) throws ServiceException;

    List<FoodIngredient> readByIngredientId(Integer ingredientId) throws ServiceException;

    void deleteByFoodId(Integer foodId) throws ServiceException;

    void deleteByIngredientId(Integer ingredientId) throws ServiceException;
}
