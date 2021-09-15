package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.*;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.*;
import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.FoodIngredient;
import by.training.cafeproject.domain.Ingredient;
import by.training.cafeproject.service.FoodIngredientService;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public class FoodIngredientServiceImpl implements FoodIngredientService {
    private DaoFactoryImpl daoFactoryObject = DaoFactoryImpl.getInstance();
    private FoodIngredientDaoImpl foodIngredientDao = daoFactoryObject.getFoodIngredientDao();
    private FoodDaoImpl foodDao = daoFactoryObject.getFoodDao();
    private IngredientDaoImpl ingredientDao = daoFactoryObject.getIngredientDao();
    private TransactionFactory transactionFactoryObject = TransactionFactoryImpl.getInstance();
    private Transaction transaction = transactionFactoryObject.getTransaction();

    @Override
    public List<FoodIngredient> readByFoodId(Integer foodId) throws ServiceException {
        try {
            transaction.initTransaction(foodDao, ingredientDao, foodIngredientDao);
            Food food = foodDao.read(foodId);
            List<FoodIngredient> foodsIngredients = foodIngredientDao.readByFoodId(foodId);
            int ingredientId;
            Ingredient ingredient;
            for (FoodIngredient foodIngredient : foodsIngredients) {
                foodIngredient.setFoodId(food);
                ingredientId = foodIngredient.getIngredientId().getId();
                ingredient = ingredientDao.read(ingredientId);
                foodIngredient.setIngredientId(ingredient);
            }
            transaction.commit();
            return foodsIngredients;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<FoodIngredient> readByIngredientId(Integer ingredientId) throws ServiceException {
        try {
            transaction.initTransaction(foodDao, ingredientDao, foodIngredientDao);
            Ingredient ingredient = ingredientDao.read(ingredientId);
            List<FoodIngredient> foodsIngredients = foodIngredientDao.readByIngredientId(ingredientId);
            int foodId;
            Food food;
            for (FoodIngredient foodIngredient : foodsIngredients) {
                foodIngredient.setIngredientId(ingredient);
                foodId = foodIngredient.getFoodId().getId();
                food = foodDao.read(foodId);
                foodIngredient.setFoodId(food);
            }
            transaction.commit();
            return foodsIngredients;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void deleteByFoodId(Integer foodId) throws ServiceException {
        try {
            transaction.initTransaction(foodIngredientDao);
            foodIngredientDao.deleteByFoodId(foodId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void deleteByIngredientId(Integer ingredientId) throws ServiceException {
        try {
            transaction.initTransaction(foodIngredientDao);
            foodIngredientDao.deleteByIngredientId(ingredientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void create(FoodIngredient entity) throws ServiceException {
        try {
            transaction.initTransaction(foodIngredientDao);
            foodIngredientDao.create(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public FoodIngredient read(Integer id) throws ServiceException {
        try {
            transaction.initTransaction(foodDao, ingredientDao, foodIngredientDao);
            FoodIngredient foodIngredient = foodIngredientDao.read(id);
            Food food = foodDao.read(foodIngredient.getFoodId().getId());
            Ingredient ingredient = ingredientDao.read(foodIngredient.getIngredientId().getId());
            foodIngredient.setFoodId(food);
            foodIngredient.setIngredientId(ingredient);
            transaction.commit();
            return foodIngredient;
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void update(FoodIngredient entity) throws ServiceException {
        try {
            transaction.initTransaction(foodIngredientDao);
            foodIngredientDao.update(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<FoodIngredient> read() throws ServiceException {
        try {
            transaction.initTransaction(foodDao, ingredientDao, foodIngredientDao);
            List<FoodIngredient> foodsIngredients = foodIngredientDao.read();
            int foodId;
            int ingredientId;
            Food food;
            Ingredient ingredient;
            for (FoodIngredient foodIngredient : foodsIngredients) {
                foodId = foodIngredient.getFoodId().getId();
                ingredientId = foodIngredient.getIngredientId().getId();
                food = foodDao.read(foodId);
                ingredient = ingredientDao.read(ingredientId);
                foodIngredient.setFoodId(food);
                foodIngredient.setIngredientId(ingredient);
            }
            transaction.commit();
            return foodsIngredients;
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
            transaction.initTransaction(foodIngredientDao);
            foodIngredientDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public void delete(FoodIngredient entity) throws ServiceException {
        try {
            transaction.initTransaction(foodIngredientDao);
            foodIngredientDao.delete(entity);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }
}
