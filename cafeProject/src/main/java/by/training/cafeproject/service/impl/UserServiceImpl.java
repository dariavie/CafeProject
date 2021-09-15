package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.Transaction;
import by.training.cafeproject.dao.TransactionFactory;
import by.training.cafeproject.dao.UserDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.DaoFactoryImpl;
import by.training.cafeproject.dao.impl.TransactionFactoryImpl;
import by.training.cafeproject.dao.impl.UserDaoImpl;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {
    private DaoFactoryImpl daoFactoryObject = DaoFactoryImpl.getInstance();
    private UserDaoImpl userDao = daoFactoryObject.getUserDao();
    private TransactionFactory transactionFactoryObject = TransactionFactoryImpl.getInstance();
    private Transaction transaction = transactionFactoryObject.getTransaction();

    @Override
    public void create(User entity) throws ServiceException {
        try {
            transaction.initTransaction(userDao);
            userDao.create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public User read(Integer id) throws ServiceException {
        try {
            transaction.initTransaction(userDao);
            return userDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void update(User entity) throws ServiceException {
        try {
            transaction.initTransaction(userDao);
            userDao.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<User> read() throws ServiceException {
        try {
            transaction.initTransaction(userDao);
            return userDao.read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            transaction.initTransaction(userDao);
            userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void delete(User entity) throws ServiceException {
        try {
            transaction.initTransaction(userDao);
            userDao.delete(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public User read(String login, String password) throws ServiceException {
        try {
            transaction.initTransaction(userDao);
            return userDao.read(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void delete(String login, String password) throws ServiceException {
        try {
            transaction.initTransaction(userDao);
            userDao.delete(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
