package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.Transaction;
import by.training.cafeproject.dao.TransactionFactory;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.DaoFactoryImpl;
import by.training.cafeproject.dao.impl.TransactionFactoryImpl;
import by.training.cafeproject.dao.impl.UserDaoImpl;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {
    private DaoFactoryImpl daoFactoryObject = DaoFactoryImpl.getInstance();
    private UserDaoImpl userDao = daoFactoryObject.getUserDao();
    private TransactionFactory transactionFactoryObject = TransactionFactoryImpl.getInstance();
    private Transaction transaction = transactionFactoryObject.getTransaction();
    private static final Logger userServiceImplLogger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public void create(User entity) throws ServiceException {
        try {
            userServiceImplLogger.info("service create start");
            transaction.initTransaction(userDao);
            userServiceImplLogger.info("service create transaction start");
            userDao.create(entity);
            userServiceImplLogger.info("service userdao create start");
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
    public User readByLogin(String login) throws ServiceException {
        try {
            transaction.initTransaction(userDao);
            return userDao.readByLogin(login);
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
