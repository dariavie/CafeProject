package by.training.cafeproject.service;

import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.service.exception.ServiceException;

import java.util.List;

public interface UserInfoService extends Service {
    List<UserInfo> findAll() throws ServiceException;

    UserInfo findById(Integer id) throws ServiceException;

    void delete(Integer id) throws ServiceException;

    UserInfo findByPhone(String phone) throws ServiceException;

    UserInfo findByEmail(String email) throws ServiceException;

    void save(UserInfo userInfo) throws ServiceException;
}
