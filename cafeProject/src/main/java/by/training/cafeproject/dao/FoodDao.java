package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Food;

import java.util.List;

public interface FoodDao extends Dao<Food> {
    List<Food> readByTitle(String title) throws DaoException;

    List<Food> readByPrice(Double price) throws DaoException;

    List<Food> readByType(Integer typeNumber) throws DaoException;

    void deleteByTitle(String title) throws DaoException;

    void deleteByType(Integer typeNumber) throws DaoException;

}
