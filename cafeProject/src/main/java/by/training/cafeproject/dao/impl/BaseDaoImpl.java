package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.Dao;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Entity;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDaoImpl {
    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void closeStatement(Statement statement) throws DaoException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void closePreparedStatement(PreparedStatement preparedStatement) throws DaoException {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


}
