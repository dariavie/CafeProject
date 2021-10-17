package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.*;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.pool.ConnectionPool;
import by.training.cafeproject.exception.PersistentException;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionImpl implements Transaction {
    private static Logger logger = Logger.getLogger(TransactionImpl.class);

    private static Map<Class<? extends Dao<?>>, Class<? extends BaseDaoImpl>> classes = new ConcurrentHashMap<>();
    static {
        classes.put(FoodDao.class, FoodDaoImpl.class);
        classes.put(FoodIngredientDao.class, FoodIngredientDaoImpl.class);
        classes.put(IngredientDao.class, IngredientDaoImpl.class);
        classes.put(OrderDao.class, OrderDaoImpl.class);
        classes.put(UserDao.class, UserDaoImpl.class);
        classes.put(UserInfoDao.class, UserInfoDaoImpl.class);
        classes.put(WorkerDao.class, WorkerDaoImpl.class);
        classes.put(OrderFoodDao.class, OrderFoodDaoImpl.class);
    }

    private Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <Type extends Dao<?>> Type createDao(Class<Type> key) throws PersistentException {
        Class<? extends BaseDaoImpl> value = classes.get(key);
        if(value != null) {
            try {
                BaseDaoImpl dao = value.newInstance();
                dao.setConnection(connection);
                return (Type)dao;
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("It is impossible to create data access object", e);
                throw new PersistentException(e);
            }
        }
        return null;
    }

    @Override
    public void commit() throws PersistentException {
        try {
            connection.commit();
        } catch(SQLException e) {
            logger.error("It is impossible to commit transaction", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch(SQLException e) {
            logger.error("It is impossible to rollback transaction", e);
            throw new PersistentException(e);
        }
    }
}
