package by.training.cafeproject.dao;

import by.training.cafeproject.entity.Food;

import java.util.List;

public interface FoodDao extends Dao<Food> {
    List<Food> readByTitle(String title);

    List<Food> readByPrice(Double price);

    List<Food> readByType(Integer typeNumber);

    void deleteByTitle(String title);

    void deleteByType(Integer typeNumber);
}
