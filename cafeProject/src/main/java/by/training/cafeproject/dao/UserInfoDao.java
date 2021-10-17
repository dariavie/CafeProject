package by.training.cafeproject.dao;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.domain.UserInfo;

import java.util.List;

public interface UserInfoDao extends Dao<UserInfo> {

    UserInfo readByPhone(String phone) throws DaoException;

    UserInfo readByEmail(String email) throws DaoException;
}
