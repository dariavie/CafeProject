package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.FoodDao;
import by.training.cafeproject.dao.IngredientDao;
import by.training.cafeproject.dao.Transaction;
import by.training.cafeproject.dao.TransactionFactory;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.DaoFactoryImpl;
import by.training.cafeproject.dao.impl.IngredientDaoImpl;
import by.training.cafeproject.dao.impl.TransactionFactoryImpl;
import by.training.cafeproject.domain.Ingredient;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.IngredientService;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public class IngredientServiceImpl extends ServiceImpl implements IngredientService {
    @Override
    public void save(Ingredient ingredient) throws ServiceException {
        try {
            IngredientDao dao = transaction.createDao(IngredientDao.class);
            if (ingredient.getId() != null) {
                dao.update(ingredient);
            } else {
                ingredient.setId(dao.create(ingredient));
            }
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Ingredient findById(Integer id) throws ServiceException {
        try {
            IngredientDao dao = transaction.createDao(IngredientDao.class);
            return dao.read(id);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Ingredient findByTitle(String title) throws ServiceException {
        try {
            IngredientDao dao = transaction.createDao(IngredientDao.class);
            return dao.readByTitle(title);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Ingredient> findAll() throws ServiceException {
        try {
            IngredientDao dao = transaction.createDao(IngredientDao.class);
            return dao.read();
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            IngredientDao dao = transaction.createDao(IngredientDao.class);
            dao.delete(id);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteByTitle(String title) throws ServiceException {
        try {
            IngredientDao dao = transaction.createDao(IngredientDao.class);
            dao.deleteByTitle(title);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }
}
