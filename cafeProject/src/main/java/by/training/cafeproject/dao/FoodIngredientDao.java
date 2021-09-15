package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.FoodIngredient;

import java.util.List;

public interface FoodIngredientDao extends Dao<FoodIngredient> {

    List<FoodIngredient> readByFoodId(Integer foodId) throws DaoException;

    List<FoodIngredient> readByIngredientId(Integer ingredientId) throws DaoException;

    void deleteByFoodId(Integer foodId) throws DaoException;

    void deleteByIngredientId(Integer ingredientId) throws DaoException;

}
