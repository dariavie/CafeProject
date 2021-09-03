package by.training.cafeproject.dao;

import by.training.cafeproject.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    User read(String login, String password);

    List<User> read();

    void delete(String login, String password);
}
