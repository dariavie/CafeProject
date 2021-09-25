package by.training.cafeproject.controller;

import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.FoodDaoImpl;
import by.training.cafeproject.dao.impl.UserDaoImpl;
import by.training.cafeproject.domain.FoodIngredient;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.service.FoodIngredientService;
import by.training.cafeproject.service.ServiceFactory;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.exception.ServiceException;
import by.training.cafeproject.service.impl.FoodIngredientServiceImpl;
import by.training.cafeproject.service.impl.ServiceFactoryImpl;

import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        ServiceFactory serviceFactory = ServiceFactoryImpl.getInstance();
        UserService userService = serviceFactory.getUserService();
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User();
        user.setId(10);
        user.setLogin("sfjblkls");
        user.setPassword("kdlkbp;alr");
        user.setRole(2);
        try {
            userService.create(user);
        } catch (ServiceException e) {
            e.getMessage();
        }
    }
}
