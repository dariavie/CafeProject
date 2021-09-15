package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.User;

public interface UserDao extends Dao<User> {
    User read(String login, String password) throws DaoException;

    void delete(String login, String password) throws DaoException;
}
