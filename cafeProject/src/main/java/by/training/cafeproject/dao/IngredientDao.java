package by.training.cafeproject.dao;

import by.training.cafeproject.entity.Ingredient;

import java.util.List;

public interface IngredientDao extends Dao<Ingredient> {
    List<Ingredient> readByTitle(String search);

    void deleteByTitle(String title);
}
