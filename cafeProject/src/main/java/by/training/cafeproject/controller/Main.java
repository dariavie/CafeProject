package by.training.cafeproject.controller;

import by.training.cafeproject.dao.TransactionFactory;
import by.training.cafeproject.dao.exception.DaoException;
import by.training.cafeproject.dao.impl.FoodDaoImpl;
import by.training.cafeproject.dao.impl.TransactionFactoryImpl;
import by.training.cafeproject.dao.impl.UserDaoImpl;
import by.training.cafeproject.dao.pool.ConnectionPool;
import by.training.cafeproject.domain.FoodIngredient;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.domain.Worker;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.*;
import by.training.cafeproject.service.exception.ServiceException;
import by.training.cafeproject.service.impl.FoodIngredientServiceImpl;
import by.training.cafeproject.service.impl.ServiceFactoryImpl;
import org.apache.log4j.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static final String LOG_FILE_NAME = "log.txt";
    public static final Level LOG_LEVEL = Level.ALL;
    public static final String LOG_MESSAGE_FORMAT = "%n%d%n%p\t%C.%M:%L%n%m%n";

    public static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/cafe_db";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "1skinnydoublespace2$";
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

    public static void main(String[] args) throws ParseException {
//        ServiceFactory serviceFactory = ServiceFactoryImpl.getInstance();
//        UserService userService = serviceFactory.getUserService();
//        UserDaoImpl userDao = new UserDaoImpl();
//        User user = new User();
//        user.setId(10);
//        user.setLogin("sfjblkls");
//        user.setPassword("kdlkbp;alr");
//        user.setRole(2);
//        try {
//            userService.create(user);
//        } catch (ServiceException e) {
//            e.getMessage();
//        }
        try {
            Logger root = Logger.getRootLogger();
            Layout layout = new PatternLayout(LOG_MESSAGE_FORMAT);
            root.addAppender(new FileAppender(layout, LOG_FILE_NAME, true));
            root.addAppender(new ConsoleAppender(layout));
            root.setLevel(LOG_LEVEL);
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch(PersistentException | IOException e) {
            logger.error("It is impossible to initialize application", e);
        }
        String login = "client_01";
        String password = "w459oklzdfo93";
        try {
            TransactionFactory transactionFactory = new TransactionFactoryImpl();
            ServiceFactoryImpl factory = new ServiceFactoryImpl(transactionFactory);
//            UserService service = factory.getService(UserService.class);
//            User user = service.findByLoginAndPassword(login, password);
            WorkerService service = factory.getService(WorkerService.class);
            Worker worker = service.findById(2);
            System.out.println(worker);
        } catch (PersistentException | ServiceException e) {
            e.getMessage();
        }
    }
}
