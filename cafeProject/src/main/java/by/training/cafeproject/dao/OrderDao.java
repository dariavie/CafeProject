package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Order;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> readByClientId(Integer clientId) throws DaoException;

    List<Order> readByClientName(String name) throws DaoException;

    List<Order> readByFoodId(Integer foodId) throws DaoException;

    List<Order> readByWorkerId(Integer workerId) throws DaoException;

    void deleteByClientId(Integer clientId) throws DaoException;

    void deleteByWorkerId(Integer workerId) throws DaoException;
}
