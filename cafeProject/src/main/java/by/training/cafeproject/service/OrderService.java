package by.training.cafeproject.service;

import by.training.cafeproject.domain.Order;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public interface OrderService extends Service<Order> {
    List<Order> readByClientId(Integer clientId) throws ServiceException;

    List<Order> readByClientName(String name) throws ServiceException;

    List<Order> readByFoodId(Integer foodId) throws ServiceException;

    List<Order> readByWorkerId(Integer workerId) throws ServiceException;

    void deleteByClientId(Integer clientId) throws ServiceException;

    void deleteByWorkerId(Integer workerId) throws ServiceException;
}
