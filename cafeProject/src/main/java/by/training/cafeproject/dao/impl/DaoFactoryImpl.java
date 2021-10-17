package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.*;

public class DaoFactoryImpl implements DaoFactory {

    private static final DaoFactoryImpl instance = new DaoFactoryImpl();

    private final FoodDaoImpl foodDao = new FoodDaoImpl();
    private final FoodIngredientDaoImpl foodIngredientDao = new FoodIngredientDaoImpl();
    private final IngredientDaoImpl ingredientDao = new IngredientDaoImpl();
    private final OrderDaoImpl orderDao = new OrderDaoImpl();
    private final UserDaoImpl userDao = new UserDaoImpl();
    private final UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
    private final WorkerDaoImpl workerDao = new WorkerDaoImpl();

    private DaoFactoryImpl() {}

    public static DaoFactoryImpl getInstance() {
        return instance;
    }

    @Override
    public FoodDaoImpl getFoodDao() {
        return foodDao;
    }

    @Override
    public FoodIngredientDaoImpl getFoodIngredientDao() {
        return foodIngredientDao;
    }

    @Override
    public IngredientDaoImpl getIngredientDao() {
        return ingredientDao;
    }

    @Override
    public OrderDaoImpl getOrderDao() {
        return orderDao;
    }

    @Override
    public UserDaoImpl getUserDao() {
        return userDao;
    }

    @Override
    public UserInfoDaoImpl getUserInfoDao() {
        return userInfoDao;
    }

    @Override
    public WorkerDaoImpl getWorkerDao() {
        return workerDao;
    }
}
