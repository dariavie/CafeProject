package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.*;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.*;
import by.training.cafeproject.domain.*;
import by.training.cafeproject.service.WorkerService;
import by.training.cafeproject.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public class WorkerServiceImpl implements WorkerService {
    private DaoFactoryImpl daoFactoryObject = DaoFactoryImpl.getInstance();
    private WorkerDaoImpl workerDao = daoFactoryObject.getWorkerDao();
    private UserInfoDaoImpl userInfoDao = daoFactoryObject.getUserInfoDao();
    private TransactionFactory transactionFactoryObject = TransactionFactoryImpl.getInstance();
    private Transaction transaction = transactionFactoryObject.getTransaction();

    @Override
    public void create(Worker entity) throws ServiceException {
        try {
            transaction.initTransaction(workerDao);
            workerDao.create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public Worker read(Integer id) throws ServiceException {
        try {
            transaction.initTransaction(workerDao, userInfoDao);
            Worker worker = workerDao.read(id);
            UserInfo userInfo = userInfoDao.read(worker.getId());
            worker.setUserInfoId(userInfo);
            transaction.commit();
            return worker;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void update(Worker entity) throws ServiceException {
        try {
            transaction.initTransaction(workerDao);
            workerDao.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Worker> read() throws ServiceException {
        try {
            transaction.initTransaction(workerDao, userInfoDao);
            List<Worker> workers = workerDao.read();
            int userInfoId;
            UserInfo userInfo;
            for (Worker worker : workers) {
                userInfoId = worker.getUserInfoId().getId();
                userInfo = userInfoDao.read(userInfoId);
                worker.setUserInfoId(userInfo);
            }
            transaction.commit();
            return workers;
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
            transaction.initTransaction(workerDao);
            workerDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void delete(Worker entity) throws ServiceException {
        try {
            transaction.initTransaction(workerDao);
            workerDao.delete(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Worker> readByStartDate(Date date) throws ServiceException {
        try {
            transaction.initTransaction(workerDao, userInfoDao);
            List<Worker> workers = workerDao.readByStartDate(date);
            int userInfoId;
            UserInfo userInfo;
            for (Worker worker : workers) {
                userInfoId = worker.getUserInfoId().getId();
                userInfo = userInfoDao.read(userInfoId);
                worker.setUserInfoId(userInfo);
            }
            transaction.commit();
            return workers;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<Worker> readByEndDate(Date date) throws ServiceException {
        try {
            transaction.initTransaction(workerDao, userInfoDao);
            List<Worker> workers = workerDao.readByEndDate(date);
            int userInfoId;
            UserInfo userInfo;
            for (Worker worker : workers) {
                userInfoId = worker.getUserInfoId().getId();
                userInfo = userInfoDao.read(userInfoId);
                worker.setUserInfoId(userInfo);
            }
            transaction.commit();
            return workers;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<Worker> readBySpecialization(String specialization) throws ServiceException {
        try {
            transaction.initTransaction(workerDao, userInfoDao);
            List<Worker> workers = workerDao.readBySpecialization(specialization);
            int userInfoId;
            UserInfo userInfo;
            for (Worker worker : workers) {
                userInfoId = worker.getUserInfoId().getId();
                userInfo = userInfoDao.read(userInfoId);
                worker.setUserInfoId(userInfo);
            }
            transaction.commit();
            return workers;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void deleteBySpecialization(String specialization) throws ServiceException {
        try {
            transaction.initTransaction(workerDao);
            workerDao.deleteBySpecialization(specialization);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
