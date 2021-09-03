package by.training.cafeproject.dao;

import by.training.cafeproject.entity.Food;
import by.training.cafeproject.entity.FoodIngredient;
import by.training.cafeproject.entity.Ingredient;

import java.util.List;

public interface FoodIngredientDao extends Dao<FoodIngredient> {
    void deleteByFoodId(Integer foodId);

    void deleteByIngredientId(Integer ingredientId);

    List<FoodIngredient> readByFoodId(Integer foodId);

    List<FoodIngredient> readByIngredientId(Integer ingredientId);

}
