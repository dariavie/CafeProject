package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.Transaction;
import by.training.cafeproject.dao.TransactionFactory;
import by.training.cafeproject.dao.UserDao;
import by.training.cafeproject.dao.UserInfoDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.DaoFactoryImpl;
import by.training.cafeproject.dao.impl.TransactionFactoryImpl;
import by.training.cafeproject.dao.impl.UserDaoImpl;
import by.training.cafeproject.dao.impl.UserInfoDaoImpl;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfoServiceImpl extends ServiceImpl implements UserInfoService {
    private static final Logger logger = Logger.getLogger(UserInfoServiceImpl.class);

    @Override
    public List<UserInfo> findAll() throws ServiceException {
        try {
            UserInfoDao dao = transaction.createDao(UserInfoDao.class);
            List<UserInfo> userInfos = dao.read();
            buildUserInfo(userInfos);
            return userInfos;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserInfo findById(Integer id) throws ServiceException {
        try {
            UserInfoDao dao = transaction.createDao(UserInfoDao.class);
            UserInfo userInfo = dao.read(id);
            if (userInfo != null) {
                buildUserInfo(Arrays.asList(userInfo));
            }
            return userInfo;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            UserInfoDao dao = transaction.createDao(UserInfoDao.class);
            dao.delete(id);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserInfo findByPhone(String phone) throws ServiceException {
        try {
            UserInfoDao dao = transaction.createDao(UserInfoDao.class);
            UserInfo userInfo = dao.readByPhone(phone);
            if (userInfo != null) {
                buildUserInfo(Arrays.asList(userInfo));
            }
            return userInfo;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserInfo findByEmail(String email) throws ServiceException {
        try {
            UserInfoDao dao = transaction.createDao(UserInfoDao.class);
            UserInfo userInfo = dao.readByEmail(email);
            if (userInfo != null) {
                buildUserInfo(Arrays.asList(userInfo));
            }
            return userInfo;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(UserInfo userInfo) throws ServiceException {
        try {
            UserInfoDao dao = transaction.createDao(UserInfoDao.class);
            if (userInfo.getId() != null) {
                logger.info("update in dao, first: " + userInfo);
                dao.update(userInfo);
                logger.info("update in dao, result: " + userInfo);
            } else {
                logger.info("create in dao, first: " + userInfo);
                userInfo.setId(dao.create(userInfo));
                logger.info("create in dao, result: " + userInfo);
            }
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    private void buildUserInfo(List<UserInfo> userInfos) throws ServiceException {
        try {
            UserDao userDao = transaction.createDao(UserDao.class);
            Map<Integer, User> users = new HashMap<>();
            User user;
            Integer id;
            for (UserInfo userInfo : userInfos) {
                user = userInfo.getUserId();
                if (user != null) {
                    id = user.getId();
                    user = users.get(id);
                    if (user == null) {
                        user = userDao.read(id);
                    }
                    userInfo.setUserId(user);
                }
            }
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }
}
