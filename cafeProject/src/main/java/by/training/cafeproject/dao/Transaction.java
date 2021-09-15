package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.BaseDaoImpl;

import java.sql.Connection;
import java.sql.Savepoint;

public interface Transaction {

    void initTransaction(BaseDaoImpl dao);

    void initTransaction(BaseDaoImpl dao, BaseDaoImpl... daos);

    void endTransaction();

    void commit();

    void rollback();

    void setSavepoint();

    void rollbackToSavepoint(Savepoint savepoint);

    void end();
}
