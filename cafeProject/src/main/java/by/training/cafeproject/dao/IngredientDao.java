package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Ingredient;

import java.util.List;

public interface IngredientDao extends Dao<Ingredient> {
    Ingredient readByTitle(String search) throws DaoException;

    void deleteByTitle(String title) throws DaoException;
}
