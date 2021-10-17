package by.training.cafeproject.service;

import by.training.cafeproject.domain.User;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public interface UserService extends Service {

    List<User> findAll() throws ServiceException;

    User findByIdentity(Integer identity) throws ServiceException;

    User findByLoginAndPassword(String login, String password) throws ServiceException;

    User findByLogin(String login) throws ServiceException;

    void save(User user) throws ServiceException;

    void delete(Integer identity) throws ServiceException;
}
