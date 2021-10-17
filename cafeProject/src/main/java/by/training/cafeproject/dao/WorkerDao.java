package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.Worker;

import java.sql.Date;
import java.util.List;

public interface WorkerDao extends Dao<Worker> {

    List<Worker> readBySpecialization(String specialization) throws DaoException;

    void deleteBySpecialization(String specialization) throws DaoException;
}
