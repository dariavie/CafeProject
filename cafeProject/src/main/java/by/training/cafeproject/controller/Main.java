package by.training.cafeproject.controller;

import by.training.cafeproject.dao.FoodDao;
import by.training.cafeproject.dao.UserDao;
import by.training.cafeproject.dao.WorkerDao;
import by.training.cafeproject.dao.impl.FoodDaoImpl;
import by.training.cafeproject.dao.impl.IngredientDaoImpl;
import by.training.cafeproject.dao.impl.UserDaoImpl;
import by.training.cafeproject.dao.impl.WorkerDaoImpl;
import by.training.cafeproject.entity.Food;
import by.training.cafeproject.entity.Ingredient;
import by.training.cafeproject.entity.User;
import by.training.cafeproject.entity.Worker;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        IngredientDaoImpl ingredientDao = new IngredientDaoImpl();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(55);
        ingredient.setTitle("cucumber");
        ingredientDao.create(ingredient);
////        System.out.println(ingredient.toString());
////        ingredient.setId(22);
//////        ingredient.setTitle("onion");
////////        System.out.println(ingredient.toString());
////////        System.out.println(ingredientDao.create(ingredient));
////        ingredient.setTitle("ginger");
////        System.out.println(ingredient.toString());
////        ingredientDao.update(ingredient);
//        System.out.println(ingredientDao.readByTitle("rice").toString());

//        UserDaoImpl userDao = new UserDaoImpl();
//        User user = new User();
//        user.setId(6);
//        user.setLogin("ma_02");
//        user.setPassword("plumbkldkf43t");
//        user.setRole(2);
//        userDao.create(user);
//        User user = userDao.read(2);
//        System.out.println(user.toString());
//        user.setLogin("manager_1");
//        userDao.update(user);
//        String login = "manager_1";
//        String password = "948596tjkjg";
//        User user = userDao.read(login, password);
//        System.out.println(user.toString());
//        List<User> users = userDao.read();
//        System.out.println(users.toString());


//        WorkerDao workerDao = new WorkerDaoImpl();
//        List<Worker> workers = workerDao.readBySpecialization("meat");
//        System.out.println(workers.toString());

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//        java.util.Date date = sdf.parse("2020-01-02");
//        java.sql.Date dateFinal = null;
//        List<Worker> workers = workerDao.readByEndDate(dateFinal);
//        System.out.println(workers.toString());
//        Worker worker = new Worker();
//        worker.setId(2);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//        java.util.Date date = sdf.parse("2019-06-05");
//        java.sql.Date dateFinal = new Date(date.getTime());
//
//        java.util.Date endDate = sdf.parse("2020-04-02");
//        java.sql.Date endDateFinal = new Date(endDate.getTime());
//
//        worker.setStartOfWork(dateFinal);
//        worker.setEndOfWork(endDateFinal);
//        worker.setSpecialization("drinks, till");
//        workerDao.update(worker);
//        System.out.println(workerDao.read(3).toString());

//        FoodDao foodDao = new FoodDaoImpl();
//        System.out.println(foodDao.readByType(1).toString());
//        Food food = new Food();
//        food.setTitle("Haha");
//        food.setDescription("hahahaha");
//        food.setPrice(44.55);
//        food.setType(2);
//        food.setId(5);
//        foodDao.update(food);
//        List<Food> foods = foodDao.read();
//        System.out.println(foods.toString());
//        Food food = new Food();
//        food.setId(4);
//        food.setTitle("Curd with fruits");
//        food.setDescription("Mix curd with fruits and topping");
//        food.setPrice(11.9);
//        food.setType(1);
//        foodDao.delete(5);
    }
}
