package by.training.cafeproject.service;

import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.exception.ServiceException;

public interface ServiceFactory {
    <Type extends Service> Type getService(Class<Type> key) throws ServiceException;

    void close();
}
