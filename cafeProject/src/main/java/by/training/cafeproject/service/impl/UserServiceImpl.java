package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.Transaction;
import by.training.cafeproject.dao.TransactionFactory;
import by.training.cafeproject.dao.UserDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.DaoFactoryImpl;
import by.training.cafeproject.dao.impl.TransactionFactoryImpl;
import by.training.cafeproject.dao.impl.UserDaoImpl;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.exception.ServiceException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Formatter;
import java.util.List;

public class UserServiceImpl extends ServiceImpl implements UserService {
    @Override
    public List<User> findAll() throws ServiceException {
        try {
            UserDao dao = transaction.createDao(UserDao.class);
            return dao.read();
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByIdentity(Integer identity) throws ServiceException {
        try {
            UserDao dao = transaction.createDao(UserDao.class);
            return dao.read(identity);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            UserDao dao = transaction.createDao(UserDao.class);
            return dao.read(login, password);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        try {
            UserDao dao = transaction.createDao(UserDao.class);
            return dao.readByLogin(login);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            UserDao dao = transaction.createDao(UserDao.class);
            if (user.getId() != null) {
                if (user.getPassword() != null) {
                    user.setPassword(user.getPassword());
                } else {
                    User oldUser = dao.read(user.getId());
                    user.setPassword(oldUser.getPassword());
                }
                dao.update(user);
            } else {
//                user.setPassword(new String());
                user.setId(dao.create(user));
            }
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer identity) throws ServiceException {
        try {
            UserDao dao = transaction.createDao(UserDao.class);
            dao.delete(identity);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }
}

