package by.training.cafeproject.service;

import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public interface UserInfoService extends Service<UserInfo> {
    List<UserInfo> readByName(String name) throws ServiceException;

    List<UserInfo> readBySurname(String surname) throws ServiceException;

    UserInfo readByPhone(String phone) throws ServiceException;

    UserInfo readByEmail(String email) throws ServiceException;

    void deleteByPhone(String phone) throws ServiceException;

    void deleteByEmail(String email) throws ServiceException;
}
