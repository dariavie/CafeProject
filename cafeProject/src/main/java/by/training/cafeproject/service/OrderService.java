package by.training.cafeproject.service;

import by.training.cafeproject.domain.Order;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public interface OrderService extends Service {
    void save(Order order) throws ServiceException;

    Order findById(Integer id) throws ServiceException;

    List<Order> findByClientId(Integer clientId) throws ServiceException;

    List<Order> findAll() throws ServiceException;

    void delete(Integer id) throws ServiceException;

    void deleteByClientId(Integer clientId) throws ServiceException;
}
