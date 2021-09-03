package by.training.cafeproject.dao;

import by.training.cafeproject.entity.Worker;

import java.sql.Date;
import java.util.List;

public interface WorkerDao extends Dao<Worker> {
    List<Worker> readByStartDate(Date date);

    List<Worker> readByEndDate(Date date);

    List<Worker> readBySpecialization(String specialization);

    void deleteBySpecialization(String specialization);
}
