package by.training.cafeproject.service;

import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.FoodType;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public interface FoodService extends Service {
    void save(Food food) throws ServiceException;

    Food findById(Integer id) throws ServiceException;

    List<Food> findAll() throws ServiceException;

    void delete(Integer id) throws ServiceException;

    Food findByTitle(String title) throws ServiceException;

    List<Food> findByType(FoodType type) throws ServiceException;

    void deleteByTitle(String title) throws ServiceException;
}
