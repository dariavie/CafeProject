package by.training.cafeproject.service;

import by.training.cafeproject.domain.Food;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public interface FoodService extends Service<Food> {
    List<Food> readByTitle(String title) throws ServiceException;

    List<Food> readByPrice(Double price) throws ServiceException;

    List<Food> readByType(Integer typeNumber) throws ServiceException;

    void deleteByTitle(String title) throws ServiceException;

    void deleteByType(Integer typeNumber) throws ServiceException;
}
