package by.training.cafeproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public interface DaoFactory {

    FoodDao getFoodDao();

    FoodIngredientDao getFoodIngredientDao();

    IngredientDao getIngredientDao();

    OrderDao getOrderDao();

    RatingDao getRatingDao();

    UserDao getUserDao();

    UserInfoDao getUserInfoDao();

    WorkerDao getWorkerDao();
}
