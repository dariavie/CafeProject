package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.FoodType;

import java.util.List;

public interface FoodDao extends Dao<Food> {
    Food readByTitle(String title) throws DaoException;

    List<Food> readByType(FoodType type) throws DaoException;

    void deleteByTitle(String title) throws DaoException;

}
