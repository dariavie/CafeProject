package by.training.cafeproject.dao;

import by.training.cafeproject.entity.Order;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> readByClientId(Integer clientId);

    List<Order> readByClientName(String name);

    List<Order> readByFoodId(Integer foodId);

    List<Order> readByWorkerId(Integer workerId);

    void deleteByClientId(Integer clientId);

    void deleteByClientName(String name);

    void deleteByWorkerId(Integer workerId);
}
