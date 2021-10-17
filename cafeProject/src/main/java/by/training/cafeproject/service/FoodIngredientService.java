package by.training.cafeproject.service;

import by.training.cafeproject.domain.FoodIngredient;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public interface FoodIngredientService extends Service {
    FoodIngredient findById(Integer id) throws ServiceException;

    List<FoodIngredient> findALl() throws ServiceException;

    void delete(Integer id) throws ServiceException;

    void deleteByFoodId(Integer foodId) throws ServiceException;

    void deleteByIngredientId(Integer ingredientId) throws ServiceException;

    List<FoodIngredient> findByFoodId(Integer foodId) throws ServiceException;

    List<FoodIngredient> findByIngredientId(Integer ingredientId) throws ServiceException;

    void save(FoodIngredient foodIngredient) throws ServiceException;
}
