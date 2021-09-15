package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.Transaction;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class TransactionImpl implements Transaction {
    public static final Logger transactionImplLogger = LogManager.getLogger(TransactionImpl.class);

    private Connection connection;

    @Override
    public void initTransaction(BaseDaoImpl dao) {
        try {
            if (connection == null) {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.init();
                connection = connectionPool.getConnection();
                dao.setConnection(connection);
            }
        } catch (Exception e) {
            transactionImplLogger.error(e);
        }
    }

    @Override
    public void initTransaction(BaseDaoImpl dao, BaseDaoImpl... daos) {
        try {
            if (connection == null) {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.init();
                connection = ConnectionPool.getInstance().getConnection();
            }
            connection.setAutoCommit(false);
        } catch (Exception e) {
            transactionImplLogger.error(e);
        }
        dao.setConnection(connection);

        for (BaseDaoImpl daoElem : daos) {
            daoElem.setConnection(connection);
        }
    }

    @Override
    public void endTransaction() {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                transactionImplLogger.error(e);
            }
        }
        connection = null;
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            transactionImplLogger.error(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            transactionImplLogger.error(e);
        }
    }

    @Override
    public void setSavepoint() {
        try {
            Savepoint savepoint = connection.setSavepoint("Savepoint");
        } catch (SQLException e) {
            transactionImplLogger.error(e);
        }
    }

    @Override
    public void rollbackToSavepoint(Savepoint savepoint) {
        try {
            connection.rollback(savepoint);
        } catch (SQLException e) {
            transactionImplLogger.error(e);
        }
    }

    @Override
    public void end() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                transactionImplLogger.error(e);
            }
        }
        connection = null;
    }
}
