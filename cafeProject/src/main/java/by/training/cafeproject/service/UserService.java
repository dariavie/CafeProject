package by.training.cafeproject.service;

import by.training.cafeproject.domain.User;
import by.training.cafeproject.service.exception.ServiceException;

public interface UserService extends Service<User> {
    User read(String login, String password) throws ServiceException;

    void delete(String login, String password) throws ServiceException;
}
