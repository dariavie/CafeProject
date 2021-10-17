package by.training.cafeproject.service;

import by.training.cafeproject.domain.Order;
import by.training.cafeproject.domain.OrderFood;
import by.training.cafeproject.service.exception.ServiceException;
import com.sun.tools.corba.se.idl.constExpr.Or;

import java.util.List;

public interface OrderFoodService extends Service {
    OrderFood findById(Integer id) throws ServiceException;

    List<OrderFood> findAll() throws ServiceException;

    void delete(Integer id) throws ServiceException;

    void deleteByOrderId(Integer orderId) throws ServiceException;

    List<OrderFood> findByOrderId(Integer orderId) throws ServiceException;

    void save(OrderFood orderFood) throws ServiceException;
}
