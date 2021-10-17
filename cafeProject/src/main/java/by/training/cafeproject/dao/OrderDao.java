package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Order;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> readByClientId(Integer clientId) throws DaoException;

    void deleteByClientId(Integer clientId) throws DaoException;
}
