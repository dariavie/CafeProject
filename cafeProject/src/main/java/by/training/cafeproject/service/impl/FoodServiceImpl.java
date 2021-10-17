package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.*;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.DaoFactoryImpl;
import by.training.cafeproject.dao.impl.FoodDaoImpl;
import by.training.cafeproject.dao.impl.TransactionFactoryImpl;
import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.FoodIngredient;
import by.training.cafeproject.domain.FoodType;
import by.training.cafeproject.domain.Ingredient;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.FoodService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class FoodServiceImpl extends ServiceImpl implements FoodService {
    private static final Logger logger = Logger.getLogger(FoodServiceImpl.class);

    @Override
    public void save(Food food) throws ServiceException {
        try {
            FoodDao dao = transaction.createDao(FoodDao.class);
            IngredientDao ingredientDao = transaction.createDao(IngredientDao.class);
            FoodIngredientDao foodIngredientDao = transaction.createDao(FoodIngredientDao.class);
            FoodIngredient foodIngredient = new FoodIngredient();
            if (food.getId() != null) {
                dao.update(food);
                logger.info("service update");
            } else {
                dao.create(food);
                food.setId(dao.readByTitle(food.getTitle()).getId());
                foodIngredient.setFoodId(food);
                List<Ingredient> ingredients = food.getIngredients();
                String title;
                for (Ingredient ingredient : ingredients) {
                    title = ingredient.getTitle();
                    ingredient = ingredientDao.readByTitle(title);
                    foodIngredient.setIngredientId(ingredient);
                    logger.info("foodIngredient: " + foodIngredient);
                    foodIngredientDao.create(foodIngredient);
                }
                logger.info("service create");
            }
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Food findById(Integer id) throws ServiceException {
        try {
            FoodDao dao = transaction.createDao(FoodDao.class);
            return dao.read(id);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Food> findAll() throws ServiceException {
        try {
            FoodDao dao = transaction.createDao(FoodDao.class);
            return dao.read();
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            FoodDao dao = transaction.createDao(FoodDao.class);
            dao.delete(id);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Food findByTitle(String title) throws ServiceException {
        try {
            FoodDao dao = transaction.createDao(FoodDao.class);
            return dao.readByTitle(title);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Food> findByType(FoodType type) throws ServiceException {
        try {
            FoodDao dao = transaction.createDao(FoodDao.class);
            return dao.readByType(type);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteByTitle(String title) throws ServiceException {
        try {
            FoodDao dao = transaction.createDao(FoodDao.class);
            dao.deleteByTitle(title);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }
}
