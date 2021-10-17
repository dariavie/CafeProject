package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.*;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.*;
import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.FoodIngredient;
import by.training.cafeproject.domain.Ingredient;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.FoodIngredientService;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodIngredientServiceImpl extends ServiceImpl implements FoodIngredientService {
    @Override
    public FoodIngredient findById(Integer id) throws ServiceException {
        try {
            FoodIngredientDao dao = transaction.createDao(FoodIngredientDao.class);
            FoodIngredient foodIngredient = dao.read(id);
            if (foodIngredient != null) {
                buildFoodIngredient(Arrays.asList(foodIngredient));
            }
            return foodIngredient;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<FoodIngredient> findALl() throws ServiceException {
        try {
            FoodIngredientDao dao = transaction.createDao(FoodIngredientDao.class);
            List<FoodIngredient> foodsIngredients = dao.read();
            buildFoodIngredient(foodsIngredients);
            return foodsIngredients;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            FoodIngredientDao dao = transaction.createDao(FoodIngredientDao.class);
            dao.delete(id);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteByFoodId(Integer foodId) throws ServiceException {
        try {
            FoodIngredientDao dao = transaction.createDao(FoodIngredientDao.class);
            dao.deleteByFoodId(foodId);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteByIngredientId(Integer ingredientId) throws ServiceException {
        try {
            FoodIngredientDao dao = transaction.createDao(FoodIngredientDao.class);
            dao.deleteByIngredientId(ingredientId);
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<FoodIngredient> findByFoodId(Integer foodId) throws ServiceException {
        try {
            FoodIngredientDao dao = transaction.createDao(FoodIngredientDao.class);
            List<FoodIngredient> foodsIngredients = dao.readByFoodId(foodId);
            buildFoodIngredient(foodsIngredients);
            return foodsIngredients;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<FoodIngredient> findByIngredientId(Integer ingredientId) throws ServiceException {
        try {
            FoodIngredientDao dao = transaction.createDao(FoodIngredientDao.class);
            List<FoodIngredient> foodsIngredients = dao.readByIngredientId(ingredientId);
            buildFoodIngredient(foodsIngredients);
            return foodsIngredients;
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(FoodIngredient foodIngredient) throws ServiceException {
        try {
            FoodIngredientDao dao = transaction.createDao(FoodIngredientDao.class);
            if (foodIngredient.getId() != null) {
                dao.update(foodIngredient);
            } else {
                foodIngredient.setId(dao.create(foodIngredient));
            }
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    private void buildFoodIngredient(List<FoodIngredient> foodsIngredients) throws ServiceException {
        try {
            FoodDao foodDao = transaction.createDao(FoodDao.class);
            IngredientDao ingredientDao = transaction.createDao(IngredientDao.class);
            Map<Integer, Food> foods = new HashMap<>();
            Map<Integer, Ingredient> ingredients = new HashMap<>();
            Food food;
            Ingredient ingredient;
            Integer id;
            for (FoodIngredient foodIngredient : foodsIngredients) {
                food = foodIngredient.getFoodId();
                if (food != null) {
                    id = food.getId();
                    food = foods.get(id);
                    if (food == null) {
                        food = foodDao.read(id);
                    }
                    foodIngredient.setFoodId(food);
                }
                ingredient = foodIngredient.getIngredientId();
                if (ingredient != null) {
                    id = ingredient.getId();
                    ingredient = ingredients.get(id);
                    if (ingredient == null) {
                        ingredient = ingredientDao.read(id);
                    }
                    foodIngredient.setIngredientId(ingredient);
                }
            }
        } catch (DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }
}
