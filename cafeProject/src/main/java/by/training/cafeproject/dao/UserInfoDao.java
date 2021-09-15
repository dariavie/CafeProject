package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.UserInfo;

import java.util.List;

public interface UserInfoDao extends Dao<UserInfo> {
    List<UserInfo> readByName(String name) throws DaoException;

    List<UserInfo> readBySurname(String surname) throws DaoException;

    UserInfo readByPhone(String phone) throws DaoException;

    UserInfo readByEmail(String email) throws DaoException;

    void deleteByPhone(String phone) throws DaoException;

    void deleteByEmail(String email) throws DaoException;
}
