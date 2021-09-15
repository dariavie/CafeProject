package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.IngredientDao;
import by.training.cafeproject.dao.Transaction;
import by.training.cafeproject.dao.TransactionFactory;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.DaoFactoryImpl;
import by.training.cafeproject.dao.impl.IngredientDaoImpl;
import by.training.cafeproject.dao.impl.TransactionFactoryImpl;
import by.training.cafeproject.domain.Ingredient;
import by.training.cafeproject.service.IngredientService;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public class IngredientServiceImpl implements IngredientService {
    private DaoFactoryImpl daoFactoryObject = DaoFactoryImpl.getInstance();
    private IngredientDaoImpl ingredientDao = daoFactoryObject.getIngredientDao();
    private TransactionFactory transactionFactoryObject = TransactionFactoryImpl.getInstance();
    private Transaction transaction = transactionFactoryObject.getTransaction();

    @Override
    public List<Ingredient> readByTitle(String search) throws ServiceException {
        try {
            transaction.initTransaction(ingredientDao);
            return ingredientDao.readByTitle(search);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void deleteByTitle(String title) throws ServiceException {
        try {
            transaction.initTransaction(ingredientDao);
            ingredientDao.deleteByTitle(title);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void create(Ingredient entity) throws ServiceException {
        try {
            transaction.initTransaction(ingredientDao);
            ingredientDao.create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public Ingredient read(Integer id) throws ServiceException {
        try {
            transaction.initTransaction(ingredientDao);
            return ingredientDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void update(Ingredient entity) throws ServiceException {
        try {
            transaction.initTransaction(ingredientDao);
            ingredientDao.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Ingredient> read() throws ServiceException {
        try {
            transaction.initTransaction(ingredientDao);
            return ingredientDao.read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            transaction.initTransaction(ingredientDao);
            ingredientDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void delete(Ingredient entity) throws ServiceException {
        try {
            transaction.initTransaction(ingredientDao);
            ingredientDao.delete(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
