package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.RatingDao;
import by.training.cafeproject.dao.Transaction;
import by.training.cafeproject.dao.TransactionFactory;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.*;
import by.training.cafeproject.domain.*;
import by.training.cafeproject.service.RatingService;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public class RatingServiceImpl implements RatingService {
    private DaoFactoryImpl daoFactoryObject = DaoFactoryImpl.getInstance();
    private RatingDaoImpl ratingDao = daoFactoryObject.getRatingDao();
    private UserDaoImpl userDao = daoFactoryObject.getUserDao();
    private FoodDaoImpl foodDao = daoFactoryObject.getFoodDao();
    private TransactionFactory transactionFactoryObject = TransactionFactoryImpl.getInstance();
    private Transaction transaction = transactionFactoryObject.getTransaction();

    @Override
    public List<Rating> readByClientId(Integer clientId) throws ServiceException {
        try {
            transaction.initTransaction(userDao, ratingDao, foodDao);
            User client = userDao.read(clientId);
            List<Rating> ratings = ratingDao.readByClientId(clientId);
            int foodId;
            Food food;
            for (Rating rating : ratings) {
                rating.setClientId(client);
                foodId = rating.getFoodId().getId();
                food = foodDao.read(foodId);
                rating.setFoodId(food);
            }
            transaction.commit();
            return ratings;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<Rating> readByClientName(String clientName) throws ServiceException {
        try {
            transaction.initTransaction(userDao, ratingDao, foodDao);
            List<Rating> ratings = ratingDao.readByClientName(clientName);
            int clientId;
            int foodId;
            User client;
            Food food;
            for (Rating rating : ratings) {
                clientId = rating.getClientId().getId();
                foodId = rating.getFoodId().getId();
                client = userDao.read(clientId);
                food = foodDao.read(foodId);
                rating.setClientId(client);
                rating.setFoodId(food);
            }
            transaction.commit();
            return ratings;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<Rating> readByFoodId(Integer foodId) throws ServiceException {
        try {
            transaction.initTransaction(userDao, ratingDao, foodDao);
            Food food = foodDao.read(foodId);
            List<Rating> ratings = ratingDao.readByFoodId(foodId);
            int clientId;
            User client;
            for (Rating rating : ratings) {
                rating.setFoodId(food);
                clientId = rating.getClientId().getId();
                client = userDao.read(clientId);
                rating.setClientId(client);
            }
            transaction.commit();
            return ratings;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<Rating> readByRating(Integer ratingInt) throws ServiceException {
        try {
            transaction.initTransaction(userDao, ratingDao, foodDao);
            List<Rating> ratings = ratingDao.readByRating(ratingInt);
            int clientId;
            int foodId;
            User client;
            Food food;
            for (Rating rating : ratings) {
                clientId = rating.getClientId().getId();
                foodId = rating.getFoodId().getId();
                client = userDao.read(clientId);
                food = foodDao.read(foodId);
                rating.setClientId(client);
                rating.setFoodId(food);
            }
            transaction.commit();
            return ratings;
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
            transaction.initTransaction(ratingDao);
            ratingDao.deleteByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void deleteByFoodId(Integer foodId) throws ServiceException {
        try {
            transaction.initTransaction(ratingDao);
            ratingDao.deleteByFoodId(foodId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void create(Rating entity) throws ServiceException {
        try {
            transaction.initTransaction(ratingDao);
            ratingDao.create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public Rating read(Integer id) throws ServiceException {
        try {
            transaction.initTransaction(userDao, ratingDao, foodDao);
            Rating rating = ratingDao.read(id);
            User client = userDao.read(rating.getClientId().getId());
            Food food = foodDao.read(rating.getFoodId().getId());
            rating.setClientId(client);
            rating.setFoodId(food);
            transaction.commit();
            return rating;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void update(Rating entity) throws ServiceException {
        try {
            transaction.initTransaction(ratingDao);
            ratingDao.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Rating> read() throws ServiceException {
        try {
            transaction.initTransaction(userDao, ratingDao, foodDao);
            List<Rating> ratings = ratingDao.read();
            int clientId;
            int foodId;
            User client;
            Food food;
            for (Rating rating : ratings) {
                clientId = rating.getClientId().getId();
                foodId = rating.getFoodId().getId();
                client = userDao.read(clientId);
                food = foodDao.read(foodId);
                rating.setClientId(client);
                rating.setFoodId(food);
            }
            transaction.commit();
            return ratings;
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
            transaction.initTransaction(ratingDao);
            ratingDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void delete(Rating entity) throws ServiceException {
        try {
            transaction.initTransaction(ratingDao);
            ratingDao.delete(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
