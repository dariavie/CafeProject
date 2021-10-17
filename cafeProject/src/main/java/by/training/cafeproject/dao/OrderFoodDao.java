package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Order;
import by.training.cafeproject.domain.OrderFood;

import java.util.List;

public interface OrderFoodDao extends Dao<OrderFood> {
    List<OrderFood> readByOrderId(Integer orderId) throws DaoException;

    void deleteByOrderId(Integer orderId) throws DaoException;
}
