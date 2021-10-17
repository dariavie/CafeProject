package by.training.cafeproject.service;

import by.training.cafeproject.domain.Worker;
import by.training.cafeproject.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public interface WorkerService extends Service {
    void save(Worker worker) throws ServiceException;

    Worker findById(Integer id) throws ServiceException;

    List<Worker> findAll() throws ServiceException;

    void delete(Integer id) throws ServiceException;

    List<Worker> findBySpecialization(String specialization) throws ServiceException;

    void deleteBySpecialization(String specialization) throws ServiceException;
}
