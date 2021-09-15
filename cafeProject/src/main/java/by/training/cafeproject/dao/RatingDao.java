package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Rating;

import java.util.List;

public interface RatingDao extends Dao<Rating> {
    List<Rating> readByClientId(Integer clientId) throws DaoException;

    List<Rating> readByClientName(String clientName) throws DaoException;

    List<Rating> readByFoodId(Integer foodId) throws DaoException;

    List<Rating> readByRating(Integer ratingInt) throws DaoException;

    void deleteByClientId(Integer clientId) throws DaoException;

    void deleteByFoodId(Integer foodId) throws DaoException;
}
