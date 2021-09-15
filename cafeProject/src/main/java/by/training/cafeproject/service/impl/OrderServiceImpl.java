package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.OrderDao;
import by.training.cafeproject.dao.Transaction;
import by.training.cafeproject.dao.TransactionFactory;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.*;
import by.training.cafeproject.domain.*;
import by.training.cafeproject.service.OrderService;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private DaoFactoryImpl daoFactoryObject = DaoFactoryImpl.getInstance();
    private OrderDaoImpl orderDao = daoFactoryObject.getOrderDao();
    private WorkerDaoImpl workerDao = daoFactoryObject.getWorkerDao();
    private UserDaoImpl userDao = daoFactoryObject.getUserDao();
    private FoodDaoImpl foodDao = daoFactoryObject.getFoodDao();
    private TransactionFactory transactionFactoryObject = TransactionFactoryImpl.getInstance();
    private Transaction transaction = transactionFactoryObject.getTransaction();

    @Override
    public List<Order> readByClientId(Integer clientId) throws ServiceException {
        try {
            transaction.initTransaction(userDao, orderDao, workerDao, foodDao);
            User client = userDao.read(clientId);
            List<Order> orders = orderDao.readByClientId(clientId);
            int workerId;
            int foodId;
            Worker worker;
            Food food;
            for (Order order : orders) {
                order.setClientId(client);
                workerId = order.getWorkerId().getId();
                foodId = order.getFoodId().getId();
                worker = workerDao.read(workerId);
                food = foodDao.read(foodId);
                order.setWorkerId(worker);
                order.setFoodId(food);
            }
            transaction.commit();
            return orders;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<Order> readByClientName(String name) throws ServiceException {
        try {
            transaction.initTransaction(orderDao, workerDao, userDao, foodDao);
            List<Order> orders = orderDao.readByClientName(name);
            int workerId;
            int clientId;
            int foodId;
            Worker worker;
            User client;
            Food food;
            for (Order order : orders) {
                workerId = order.getWorkerId().getId();
                clientId = order.getClientId().getId();
                foodId = order.getFoodId().getId();
                worker = workerDao.read(workerId);
                client = userDao.read(clientId);
                food = foodDao.read(foodId);
                order.setWorkerId(worker);
                order.setClientId(client);
                order.setFoodId(food);
            }
            transaction.commit();
            return orders;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<Order> readByFoodId(Integer foodId) throws ServiceException {
        try {
            transaction.initTransaction(foodDao, orderDao, workerDao, userDao);
            Food food = foodDao.read(foodId);
            List<Order> orders = orderDao.readByFoodId(foodId);
            int workerId;
            int clientId;
            Worker worker;
            User client;
            for (Order order : orders) {
                order.setFoodId(food);
                workerId = order.getWorkerId().getId();
                clientId = order.getClientId().getId();
                worker = workerDao.read(workerId);
                client = userDao.read(clientId);
                order.setWorkerId(worker);
                order.setClientId(client);
            }
            transaction.commit();
            return orders;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<Order> readByWorkerId(Integer workerId) throws ServiceException {
        try {
            transaction.initTransaction(workerDao, orderDao, userDao, foodDao);
            Worker worker = workerDao.read(workerId);
            List<Order> orders = orderDao.readByWorkerId(workerId);
            int clientId;
            int foodId;
            User client;
            Food food;
            for (Order order : orders) {
                order.setWorkerId(worker);
                clientId = order.getClientId().getId();
                foodId = order.getFoodId().getId();
                client = userDao.read(clientId);
                food = foodDao.read(foodId);
                order.setClientId(client);
                order.setFoodId(food);
            }
            transaction.commit();
            return orders;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void deleteByClientId(Integer clientId) throws ServiceException {
        try {
            transaction.initTransaction(orderDao);
            orderDao.deleteByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void deleteByWorkerId(Integer workerId) throws ServiceException {
        try {
            transaction.initTransaction(orderDao);
            orderDao.deleteByWorkerId(workerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void create(Order entity) throws ServiceException {
        try {
            transaction.initTransaction(orderDao);
            orderDao.create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public Order read(Integer id) throws ServiceException {
        try {
            transaction.initTransaction(orderDao, workerDao, userDao, foodDao);
            Order order = orderDao.read(id);
            Worker worker = workerDao.read(order.getWorkerId().getId());
            User client = userDao.read(order.getClientId().getId());
            Food food = foodDao.read(order.getFoodId().getId());
            order.setWorkerId(worker);
            order.setClientId(client);
            order.setFoodId(food);
            transaction.commit();
            return order;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void update(Order entity) throws ServiceException {
        try {
            transaction.initTransaction(orderDao);
            orderDao.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Order> read() throws ServiceException {
        try {
            transaction.initTransaction(orderDao, workerDao, userDao, foodDao);
            List<Order> orders = orderDao.read();
            int workerId;
            int clientId;
            int foodId;
            Worker worker;
            User client;
            Food food;
            for (Order order : orders) {
                workerId = order.getWorkerId().getId();
                clientId = order.getClientId().getId();
                foodId = order.getFoodId().getId();
                worker = workerDao.read(workerId);
                client = userDao.read(clientId);
                food = foodDao.read(foodId);
                order.setWorkerId(worker);
                order.setClientId(client);
                order.setFoodId(food);
            }
            transaction.commit();
            return orders;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            transaction.initTransaction(orderDao);
            orderDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void delete(Order entity) throws ServiceException {
        try {
            transaction.initTransaction(orderDao);
            orderDao.delete(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
