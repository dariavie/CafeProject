package by.training.cafeproject.service;

import by.training.cafeproject.domain.Rating;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public interface RatingService extends Service<Rating> {
    List<Rating> readByClientId(Integer clientId) throws ServiceException;

    List<Rating> readByClientName(String clientName) throws ServiceException;

    List<Rating> readByFoodId(Integer foodId) throws ServiceException;

    List<Rating> readByRating(Integer ratingInt) throws ServiceException;

    void deleteByClientId(Integer clientId) throws ServiceException;

    void deleteByFoodId(Integer foodId) throws ServiceException;
}
