package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.Transaction;
import by.training.cafeproject.dao.TransactionFactory;
import by.training.cafeproject.dao.UserInfoDao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.DaoFactoryImpl;
import by.training.cafeproject.dao.impl.TransactionFactoryImpl;
import by.training.cafeproject.dao.impl.UserDaoImpl;
import by.training.cafeproject.dao.impl.UserInfoDaoImpl;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public class UserInfoServiceImpl implements UserInfoService {
    private DaoFactoryImpl daoFactoryObject = DaoFactoryImpl.getInstance();
    private UserInfoDaoImpl userInfoDao = daoFactoryObject.getUserInfoDao();
    private UserDaoImpl userDao = daoFactoryObject.getUserDao();
    private TransactionFactory transactionFactoryObject = TransactionFactoryImpl.getInstance();
    private Transaction transaction = transactionFactoryObject.getTransaction();

    @Override
    public void create(UserInfo entity) throws ServiceException {
        try {
            transaction.initTransaction(userInfoDao);
            userInfoDao.create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public UserInfo read(Integer id) throws ServiceException {
        try {
            transaction.initTransaction(userInfoDao, userDao);
            UserInfo userInfo = userInfoDao.read(id);
            User user = userDao.read(id);
            userInfo.setUserId(user);
            transaction.commit();
            return userInfo;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void update(UserInfo entity) throws ServiceException {
        try {
            transaction.initTransaction(userInfoDao);
            userInfoDao.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<UserInfo> read() throws ServiceException {
        try {
            transaction.initTransaction(userInfoDao, userDao);
            List<UserInfo> userInfos = userInfoDao.read();
            int userId;
            User user;
            for (UserInfo userInfo : userInfos) {
                if (userInfo.getId()!=null) {
                    userId = userInfo.getId();
                    user = userDao.read(userId);
                    userInfo.setUserId(user);
                }
            }
            transaction.commit();
            return userInfos;
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
            transaction.initTransaction(userInfoDao);
            userInfoDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void delete(UserInfo entity) throws ServiceException {
        try {
            transaction.initTransaction(userInfoDao);
            userInfoDao.delete(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<UserInfo> readByName(String name) throws ServiceException {
        try {
            transaction.initTransaction(userInfoDao, userDao);
            List<UserInfo> userInfos = userInfoDao.readByName(name);
            int userId;
            User user;
            for (UserInfo userInfo : userInfos) {
                if (userInfo.getId()!=null) {
                    userId = userInfo.getId();
                    user = userDao.read(userId);
                    userInfo.setUserId(user);
                }
            }
            transaction.commit();
            return userInfos;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<UserInfo> readBySurname(String surname) throws ServiceException {
        try {
            transaction.initTransaction(userInfoDao, userDao);
            List<UserInfo> userInfos = userInfoDao.readBySurname(surname);
            int userId;
            User user;
            for (UserInfo userInfo : userInfos) {
                if (userInfo.getId()!=null) {
                    userId = userInfo.getId();
                    user = userDao.read(userId);
                    userInfo.setUserId(user);
                }
            }
            transaction.commit();
            return userInfos;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public UserInfo readByPhone(String phone) throws ServiceException {
        try {
            transaction.initTransaction(userInfoDao, userDao);
            UserInfo userInfo = userInfoDao.readByPhone(phone);
            if (userInfo.getId()==null) {
                transaction.commit();
                return null;
            } else {
                User user = userDao.read(userInfo.getId());
                userInfo.setUserId(user);
                transaction.commit();
                return userInfo;
            }
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public UserInfo readByEmail(String email) throws ServiceException {
        try {
            transaction.initTransaction(userInfoDao, userDao);
            UserInfo userInfo = userInfoDao.readByEmail(email);
            if (userInfo.getId()==null) {
                transaction.commit();
                return null;
            } else {
                User user = userDao.read(userInfo.getId());
                userInfo.setUserId(user);
                transaction.commit();
                return userInfo;
            }
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void deleteByPhone(String phone) throws ServiceException {
        try {
            transaction.initTransaction(userInfoDao);
            userInfoDao.deleteByPhone(phone);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void deleteByEmail(String email) throws ServiceException {
        try {
            transaction.initTransaction(userInfoDao);
            userInfoDao.deleteByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
