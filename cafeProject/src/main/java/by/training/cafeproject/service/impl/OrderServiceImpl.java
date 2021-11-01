package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.*;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.*;
import by.training.cafeproject.domain.*;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.OrderService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.*;

public class OrderServiceImpl extends ServiceImpl implements OrderService {
    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Override
    public void save(Order order) throws ServiceException {
        try {
            OrderDao dao = transaction.createDao(OrderDao.class);
            OrderFoodDao orderFoodDao = transaction.createDao(OrderFoodDao.class);
            FoodDao foodDao = transaction.createDao(FoodDao.class);
            UserInfoDao userInfoDao = transaction.createDao(UserInfoDao.class);
            UserDao userDao = transaction.createDao(UserDao.class);
            OrderFood orderFood = new OrderFood();
            List<Food> foods = new ArrayList<>();
            if (order.getId() != null) {
                dao.update(order);
                foods = order.getFoods();
                List<OrderFood> orderFoods = orderFoodDao.readByOrderId(order.getId());
                int id = order.getClientId().getId();
                UserInfo clientInfo = userInfoDao.read(id);
                order.setClientId(clientInfo);
                boolean isExist = false;
                for (OrderFood orderFoodTemp : orderFoods) {
                    orderFoodTemp.setOrderId(order);
                    orderFoodTemp.setFoodId(foodDao.read(orderFoodTemp.getFoodId().getId()));
                    for (Food food : foods) {
                        logger.info("orderFoodTemp: " + orderFoodTemp);
                        logger.info("foodId from orderFoodTemp: " + orderFoodTemp.getFoodId());
                        logger.info("title of foodId from orderFoodTemp: " + orderFoodTemp.getFoodId().getTitle());
                        logger.info("title of food: " + food.getTitle());
                        if (orderFoodTemp.getFoodId().getTitle().equals(food.getTitle())) {
                            isExist = true;
                        }
                    }
                    if (!isExist) {
                        orderFoodDao.delete(orderFoodTemp.getId());
                    }
                    isExist = false;
                }
                for (Food food : foods) {
                    for (OrderFood orderFoodTemp : orderFoods) {
                        if (food.getTitle().equals(orderFoodTemp.getFoodId().getTitle())) {
                            isExist = true;
                        }
                    }
                    if (!isExist) {
                        orderFood = new OrderFood();
                        orderFood.setOrderId(order);
                        orderFood.setFoodId(food);
                        orderFoodDao.create(orderFood);
                    }
                    isExist = false;
                }
            } else {
                orderFood = new OrderFood();
                order.setId(dao.create(order));
                int clientId = order.getClientId().getId();
                UserInfo userInfo = userInfoDao.read(clientId);
                order.setClientId(userInfo);
                orderFood.setOrderId(order);
                foods = order.getFoods();
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
            logger.info("start of find by id");
            OrderDao dao = transaction.createDao(OrderDao.class);
            Order order = dao.read(id);
            logger.info("order if found");
            if (order != null) {
                buildOrder(Arrays.asList(order));
                logger.info("order is built");
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
            OrderFoodDao orderFoodDao = transaction.createDao(OrderFoodDao.class);
            orderFoodDao.deleteByOrderId(id);
            dao.delete(id);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteByClientId(Integer clientId) throws ServiceException {
        try {
            OrderDao dao = transaction.createDao(OrderDao.class);
            OrderFoodDao orderFoodDao = transaction.createDao(OrderFoodDao.class);
            List<Order> orders = dao.readByClientId(clientId);
            int id;
            for (Order order : orders) {
                id = order.getId();
                orderFoodDao.deleteByOrderId(id);
            }
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
