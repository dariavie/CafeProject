package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.FoodDao;
import by.training.cafeproject.dao.Transaction;
import by.training.cafeproject.dao.TransactionFactory;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.DaoFactoryImpl;
import by.training.cafeproject.dao.impl.FoodDaoImpl;
import by.training.cafeproject.dao.impl.TransactionFactoryImpl;
import by.training.cafeproject.domain.Food;
import by.training.cafeproject.service.FoodService;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public class FoodServiceImpl implements FoodService {
    private DaoFactoryImpl daoFactoryObject = DaoFactoryImpl.getInstance();
    private FoodDaoImpl foodDao = daoFactoryObject.getFoodDao();
    private TransactionFactory transactionFactory = TransactionFactoryImpl.getInstance();
    private Transaction transaction = transactionFactory.getTransaction();

    @Override
    public List<Food> readByTitle(String title) throws ServiceException {
        try {
            transaction.initTransaction(foodDao);
            return foodDao.readByTitle(title);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Food> readByPrice(Double price) throws ServiceException {
        try {
            transaction.initTransaction(foodDao);
            return foodDao.readByPrice(price);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Food> readByType(Integer typeNumber) throws ServiceException {
        try {
            transaction.initTransaction(foodDao);
            return foodDao.readByType(typeNumber);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void deleteByTitle(String title) throws ServiceException {
        try {
            transaction.initTransaction(foodDao);
            foodDao.deleteByTitle(title);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void deleteByType(Integer typeNumber) throws ServiceException {
        try {
            transaction.initTransaction(foodDao);
            foodDao.deleteByType(typeNumber);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void create(Food entity) throws ServiceException {
        try {
            transaction.initTransaction(foodDao);
            foodDao.create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public Food read(Integer id) throws ServiceException {
        try {
            transaction.initTransaction(foodDao);
            return foodDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void update(Food entity) throws ServiceException {
        try {
            transaction.initTransaction(foodDao);
            foodDao.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Food> read() throws ServiceException {
        try {
            transaction.initTransaction(foodDao);
            return foodDao.read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            transaction.initTransaction(foodDao);
            foodDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void delete(Food entity) throws ServiceException {
        try {
            transaction.initTransaction(foodDao);
            foodDao.delete(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
