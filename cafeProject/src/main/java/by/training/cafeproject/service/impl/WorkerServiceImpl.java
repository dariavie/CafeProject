package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.*;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.*;
import by.training.cafeproject.domain.*;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.WorkerService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerServiceImpl extends ServiceImpl implements WorkerService {
    private static final Logger logger = Logger.getLogger(WorkerServiceImpl.class);

    @Override
    public void save(Worker worker) throws ServiceException {
        try {
            WorkerDao dao = transaction.createDao(WorkerDao.class);
            if (worker.getId() != null) {
                logger.info("start of worker service update");
                dao.update(worker);
            } else {
                logger.info("start of worker service create");
                worker.setId(dao.create(worker));
            }
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Worker findById(Integer id) throws ServiceException {
        try {
            WorkerDao dao = transaction.createDao(WorkerDao.class);
            Worker worker = dao.read(id);
            logger.info("worker1: " + worker);
            if (worker != null) {
                buildWorker(Arrays.asList(worker));
            }
            logger.info("worker2: " + worker);
            return worker;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Worker> findAll() throws ServiceException {
        try {
            WorkerDao dao = transaction.createDao(WorkerDao.class);
            List<Worker> workers = dao.read();
            buildWorker(workers);
            return workers;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            WorkerDao dao = transaction.createDao(WorkerDao.class);
            dao.delete(id);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Worker> findBySpecialization(String specialization) throws ServiceException {
        try {
            WorkerDao dao = transaction.createDao(WorkerDao.class);
            List<Worker> workers = dao.readBySpecialization(specialization);
            buildWorker(workers);
            return workers;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteBySpecialization(String specialization) throws ServiceException {
        try {
            WorkerDao dao = transaction.createDao(WorkerDao.class);
            dao.deleteBySpecialization(specialization);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    private void buildWorker(List<Worker> workers) throws ServiceException {
        try {
            UserInfoDao userInfoDao = transaction.createDao(UserInfoDao.class);
            UserDao userDao = transaction.createDao(UserDao.class);
            Map<Integer, UserInfo> userInfos = new HashMap<>();
            Map<Integer, User> users = new HashMap<>();
            UserInfo userInfo;
            User user;
            Integer id;
            for (Worker worker : workers) {
                userInfo = worker.getUserInfoId();
                if (userInfo.getId() != null) {
                    id = userInfo.getId();
                    userInfo = userInfos.get(id);
                    if (userInfo == null) {
                        userInfo = userInfoDao.read(id);
                    }
                    user = userInfo.getUserId();
                    if (user.getId() != null) {
                        id = user.getId();
                        user = users.get(id);
                        if (user == null) {
                            user = userDao.read(id);
                        }
                        userInfo.setUserId(user);
                    }
                    worker.setUserInfoId(userInfo);
                }
            }
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }
}
