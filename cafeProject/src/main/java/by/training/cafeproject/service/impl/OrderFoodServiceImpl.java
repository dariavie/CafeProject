package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.FoodDao;
import by.training.cafeproject.dao.FoodIngredientDao;
import by.training.cafeproject.dao.OrderDao;
import by.training.cafeproject.dao.OrderFoodDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.FoodIngredient;
import by.training.cafeproject.domain.Order;
import by.training.cafeproject.domain.OrderFood;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.OrderFoodService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFoodServiceImpl extends ServiceImpl implements OrderFoodService {
    private static final Logger logger = Logger.getLogger(OrderFoodServiceImpl.class);

    @Override
    public OrderFood findById(Integer id) throws ServiceException {
        try {
            OrderFoodDao dao = transaction.createDao(OrderFoodDao.class);
            OrderFood orderFood = dao.read(id);
            if (orderFood != null) {
                buildOrderFood(Arrays.asList(orderFood));
            }
            return orderFood;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderFood> findAll() throws ServiceException {
        try {
            OrderFoodDao dao = transaction.createDao(OrderFoodDao.class);
            List<OrderFood> orderFoods = dao.read();
            buildOrderFood(orderFoods);
            return orderFoods;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            OrderFoodDao dao = transaction.createDao(OrderFoodDao.class);
            dao.delete(id);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteByOrderId(Integer orderId) throws ServiceException {
        try {
            OrderFoodDao dao = transaction.createDao(OrderFoodDao.class);
            dao.deleteByOrderId(orderId);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderFood> findByOrderId(Integer orderId) throws ServiceException {
        try {
            logger.info("start findByOrderId service");
            OrderFoodDao dao = transaction.createDao(OrderFoodDao.class);
            List<OrderFood> ordersFoods = dao.readByOrderId(orderId);
            buildOrderFood(ordersFoods);
            logger.info("orderFoods from service: " + ordersFoods);
            return ordersFoods;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(OrderFood orderFood) throws ServiceException {
        try {
            OrderFoodDao dao = transaction.createDao(OrderFoodDao.class);
            if (orderFood.getId() != null) {
                dao.update(orderFood);
            } else {
                orderFood.setId(dao.create(orderFood));
            }
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    private void buildOrderFood(List<OrderFood> orderFoods) throws ServiceException {
        try {
            OrderDao orderDao = transaction.createDao(OrderDao.class);
            FoodDao foodDao = transaction.createDao(FoodDao.class);
            Map<Integer, Order> orders = new HashMap<>();
            Map<Integer, Food> foods = new HashMap<>();
            Order order;
            Food food;
            Integer id;
            for (OrderFood orderFood : orderFoods) {
                order = orderFood.getOrderId();
                if (order != null) {
                    id = order.getId();
                    order = orders.get(id);
                    if (order == null) {
                        order = orderDao.read(id);
                    }
                    orderFood.setOrderId(order);
                }
                food = orderFood.getFoodId();
                if (food != null) {
                    id = food.getId();
                    food = foods.get(id);
                    if (food == null) {
                        food = foodDao.read(id);
                    }
                    orderFood.setFoodId(food);
                }
            }
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }
}
