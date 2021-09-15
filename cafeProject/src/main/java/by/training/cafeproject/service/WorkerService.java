package by.training.cafeproject.service;

import by.training.cafeproject.domain.Worker;
import by.training.cafeproject.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public interface WorkerService extends Service<Worker> {
    List<Worker> readByStartDate(Date date) throws ServiceException;

    List<Worker> readByEndDate(Date date) throws ServiceException;

    List<Worker> readBySpecialization(String specialization) throws ServiceException;

    void deleteBySpecialization(String specialization) throws ServiceException;
}
