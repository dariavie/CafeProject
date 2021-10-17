package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.*;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.*;
import by.training.cafeproject.domain.*;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.OrderService;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.*;

public class OrderServiceImpl extends ServiceImpl implements OrderService {
    @Override
    public void save(Order order) throws ServiceException {
        try {
            OrderDao dao = transaction.createDao(OrderDao.class);
            OrderFoodDao orderFoodDao = transaction.createDao(OrderFoodDao.class);
            FoodDao foodDao = transaction.createDao(FoodDao.class);
            OrderFood orderFood = new OrderFood();
            if (order.getId() != null) {
                dao.update(order);
            } else {
                order.setId(dao.create(order));
                orderFood.setOrderId(order);
                List<Food> foods = order.getFoods();
                String title;
                for (Food food : foods) {
                    title = food.getTitle();
                    food = foodDao.readByTitle(title);
                    orderFood.setFoodId(food);
                    orderFoodDao.create(orderFood);
                }
            }
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order findById(Integer id) throws ServiceException {
        try {
            OrderDao dao = transaction.createDao(OrderDao.class);
            Order order = dao.read(id);
            if (order != null) {
                buildOrder(Arrays.asList(order));
            }
            return order;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findByClientId(Integer clientId) throws ServiceException {
        try {
            OrderDao dao = transaction.createDao(OrderDao.class);
            List<Order> orders = dao.readByClientId(clientId);
            buildOrder(orders);
            return orders;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        try {
            OrderDao dao = transaction.createDao(OrderDao.class);
            List<Order> orders = dao.read();
            buildOrder(orders);
            return orders;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            OrderDao dao = transaction.createDao(OrderDao.class);
            dao.delete(id);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteByClientId(Integer clientId) throws ServiceException {
        try {
            OrderDao dao = transaction.createDao(OrderDao.class);
            dao.deleteByClientId(clientId);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    private void buildOrder(List<Order> orders) throws ServiceException {
        try {
            UserInfoDao clientDao = transaction.createDao(UserInfoDao.class);
            OrderFoodDao orderFoodDao = transaction.createDao(OrderFoodDao.class);
            FoodDao foodDao = transaction.createDao(FoodDao.class);
            Map<Integer, UserInfo> clients = new HashMap<>();
            ArrayList<Food> foods = new ArrayList<>();
            List<OrderFood> orderFoods = new ArrayList<>();
            UserInfo client;
            Integer id;
            for (Order order : orders) {
                client = order.getClientId();
                if (client != null) {
                    id = client.getId();
                    client = clients.get(id);
                    if (client == null) {
                        client = clientDao.read(id);
                    }
                    order.setClientId(client);
                }
                foods = new ArrayList<>();
                id = order.getId();
                orderFoods = orderFoodDao.readByOrderId(id);
                for (OrderFood orderFood : orderFoods) {
                    foods.add(foodDao.read(orderFood.getFoodId().getId()));
                }
                order.setFoods(foods);
            }
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }
}
