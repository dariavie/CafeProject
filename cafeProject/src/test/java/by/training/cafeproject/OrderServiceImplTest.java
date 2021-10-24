package by.training.cafeproject;

import by.training.cafeproject.controller.DispatcherServlet;
import by.training.cafeproject.dao.impl.TransactionFactoryImpl;
import by.training.cafeproject.dao.pool.ConnectionPool;
import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.Order;
import by.training.cafeproject.domain.OrderStatus;
import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.FoodService;
import by.training.cafeproject.service.OrderService;
import by.training.cafeproject.service.ServiceFactory;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.exception.ServiceException;
import by.training.cafeproject.service.impl.ServiceFactoryImpl;
import org.apache.log4j.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class OrderServiceImplTest {
    private static Order order = new Order();
    private static Order resultOrder = new Order();
    private ServiceFactory factory;
    private OrderService orderService;
    private FoodService foodService;
    private UserInfoService userInfoService;
    private ArrayList<Food> foods;
    private List<Order> resultOrders;

    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);

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

    public void init() {
        try {
            Logger root = Logger.getRootLogger();
            Layout layout = new PatternLayout(LOG_MESSAGE_FORMAT);
            root.addAppender(new FileAppender(layout, LOG_FILE_NAME, true));
            root.addAppender(new ConsoleAppender(layout));
            root.setLevel(LOG_LEVEL);
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
            factory = new ServiceFactoryImpl(new TransactionFactoryImpl());
        } catch(PersistentException | IOException | ServiceException e) {
            logger.error("It is impossible to initialize application", e);
        }
    }

    @DataProvider(name = "positiveDataForSave")
    public Object[][] createPositiveDataForSave() throws ServiceException {
        init();
        foodService = factory.getService(FoodService.class);
        return new Object[][] {
                {42, 10.10, OrderStatus.DONE, foodService.findById(1), foodService.findById(2)},
                {26, 8.02, OrderStatus.INPROGRESS, foodService.findById(3), foodService.findById(13)},
                {38, 1.0, OrderStatus.DONE, foodService.findById(3), foodService.findById(4)},
                {40, 105.05, OrderStatus.INPROGRESS, foodService.findById(1), foodService.findById(13)}
        };
    }

    @DataProvider(name = "positiveDataForSaveUpdate")
    public Object[][] createPositiveDataForSaveUpdate() throws ServiceException {
        init();
        foodService = factory.getService(FoodService.class);
        return new Object[][] {
                {7, 6, 34.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(1), foodService.findById(2)))},
                {7, 5, 34.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(1), foodService.findById(2)))},
                {7, 5, 40.01, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(1), foodService.findById(2)))},
                {7, 5, 34.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(1), foodService.findById(2)))},
                {7, 5, 34.44, OrderStatus.INPROGRESS, new ArrayList<>(Arrays.asList(foodService.findById(1), foodService.findById(2)))},
                {7, 5, 34.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(1), foodService.findById(2)))},
                {7, 5, 34.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(1)))},
                {7, 5, 34.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(2)))},
                {7, 5, 34.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(1), foodService.findById(2), foodService.findById(3)))},
                {7, 5, 34.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(1), foodService.findById(2)))},

                {8, 6, 21.99, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(2)))},
                {8, 5, 21.99, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(2)))},
                {8, 5, 22.22, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(2)))},
                {8, 5, 21.99, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(2)))},
                {8, 5, 21.99, OrderStatus.INPROGRESS, new ArrayList<>(Arrays.asList(foodService.findById(2)))},
                {8, 5, 21.99, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(2)))},
                {8, 5, 21.99, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(2), foodService.findById(1)))},
                {8, 5, 21.99, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(1)))},
                {8, 5, 21.99, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(2)))},

                {9, 5, 44.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(4)))},
                {9, 6, 44.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(4)))},
                {9, 6, 12.22, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(4)))},
                {9, 6, 44.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(4)))},
                {9, 6, 44.44, OrderStatus.INPROGRESS, new ArrayList<>(Arrays.asList(foodService.findById(4)))},
                {9, 6, 44.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(4)))},
                {9, 6, 44.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(3), foodService.findById(2)))},
                {9, 6, 44.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(1)))},
                {9, 6, 44.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(4), foodService.findById(1)))},
                {9, 6, 44.44, OrderStatus.DONE, new ArrayList<>(Arrays.asList(foodService.findById(4)))}
        };
    }

    @DataProvider(name = "positiveDataForFindById")
    public Object[][] createPositiveDataForFindByID() {
        init();
        return new Object[][] {
                {7, 5, 34.44, OrderStatus.DONE},
                {8, 5, 21.99, OrderStatus.DONE},
                {9, 6, 44.44, OrderStatus.DONE}
        };
    }

    @DataProvider(name = "positiveDataForFindByClientId")
    public Object[][] createPositiveDataForFindByClientId() throws ServiceException{
        init();
        userInfoService = factory.getService(UserInfoService.class);
        return new Object[][] {
                {5, new ArrayList(Arrays.asList(new Order(7, userInfoService.findById(5), 34.44, OrderStatus.DONE), new Order(8, userInfoService.findById(5), 21.99, OrderStatus.DONE)))},
                {6, new ArrayList(Arrays.asList(new Order(9, userInfoService.findById(6), 44.44, OrderStatus.DONE)))}
        };
    }

    @DataProvider(name = "positiveDataForFindAll")
    public Object[][] createPositiveDataForFindAll() throws ServiceException {
        init();
        userInfoService = factory.getService(UserInfoService.class);
        return new Object[][] {
                {new ArrayList(Arrays.asList(new Order(7, userInfoService.findById(5),
                        34.44, OrderStatus.DONE),
                        new Order(8, userInfoService.findById(5), 21.99,
                                OrderStatus.DONE),
                        new Order(9, userInfoService.findById(6), 44.44, OrderStatus.DONE)))}
        };
    }

    @DataProvider(name = "invalidDataForSave")
    public Object[][] createInvalidDataForSave() throws ServiceException {
        init();
        foodService = factory.getService(FoodService.class);
        return new Object[][] {
                {0, 105.05, OrderStatus.INPROGRESS, foodService.findById(3), foodService.findById(13)},
                {40, 00.00, OrderStatus.INPROGRESS, foodService.findById(3), foodService.findById(13)},
                {40, 105.05, null, foodService.findById(3), foodService.findById(13)},
        };
    }

    @Test(description = "Positive scenario of save method", dataProvider = "positiveDataForSave")
    public void testSave(Integer clientId, Double price, OrderStatus orderStatus, Food food1, Food food2) throws ServiceException {
        foods = new ArrayList<>();
        order = new Order();
        foods.add(food1);
        foods.add(food2);
        order.setClientId(new UserInfo(clientId));
        order.setPrice(price);
        order.setOrderStatus(orderStatus);
        order.setFoods(foods);
        orderService = factory.getService(OrderService.class);
        orderService.save(order);
        int id = order.getId();
        resultOrder = orderService.findById(id);
        assertEquals(order, resultOrder);
        orderService.delete(id);
    }

    @Test(description = "Positive scenario of update part in save method", dataProvider = "positiveDataForSaveUpdate")
    public void testSaveUpdate(Integer orderId, Integer clientId, Double price, OrderStatus status, ArrayList foods) throws ServiceException {
        orderService = factory.getService(OrderService.class);
        order = new Order(orderId, new UserInfo(clientId), price, status);
        order.setFoods(foods);
        orderService.save(order);
        resultOrder = orderService.findById(orderId);
        assertEquals(resultOrder, order);
    }

    @Test(description = "Positive scenario of findById method", dataProvider = "positiveDataForFindById")
    public void testFindById(Integer orderId, Integer clientId, Double price, OrderStatus status) throws ServiceException {
        order = new Order();
        userInfoService = factory.getService(UserInfoService.class);
        orderService = factory.getService(OrderService.class);
        order.setId(orderId);
        order.setClientId(userInfoService.findById(clientId));
        order.setPrice(price);
        order.setOrderStatus(status);
        resultOrder = orderService.findById(orderId);
        assertEquals(resultOrder, order);
    }

    @Test(description = "Positive scenario of findByClientId method", dataProvider = "positiveDataForFindByClientId")
    public void testFindByClientId(Integer clientId, ArrayList orders) throws ServiceException {
        orderService = factory.getService(OrderService.class);
        resultOrders = orderService.findByClientId(clientId);
        assertEquals(resultOrders, orders);
    }

    @Test(description = "Positive scenario of findAll method", dataProvider = "positiveDataForFindAll")
    public void testFindAll(ArrayList orders) throws ServiceException {
        orderService = factory.getService(OrderService.class);
        resultOrders = orderService.findAll();
        assertEquals(resultOrders, orders);
    }

    @Test(description = "Positive scenario of delete method", dataProvider = "positiveDataForSave")
    public void testDelete(Integer clientId, Double price, OrderStatus orderStatus, Food food1, Food food2) throws ServiceException {
        foods = new ArrayList<>();
        order = new Order();
        foods.add(food1);
        foods.add(food2);
        order.setClientId(new UserInfo(clientId));
        order.setPrice(price);
        order.setOrderStatus(orderStatus);
        order.setFoods(foods);
        orderService = factory.getService(OrderService.class);
        orderService.save(order);
        int id = order.getId();
        boolean isDeleted = false;
        orderService.delete(id);
        try {
            orderService.findById(id);
        } catch (NullPointerException e) {
            isDeleted = true;
        }
        assertEquals(isDeleted, true);
    }

    @Test(description = "Positive scenario of deleteByClientId method", dataProvider = "positiveDataForSave")
    public void testDeleteByClientId(Integer clientId, Double price, OrderStatus orderStatus, Food food1, Food food2) throws ServiceException {
        foods = new ArrayList<>();
        order = new Order();
        foods.add(food1);
        foods.add(food2);
        order.setClientId(new UserInfo(clientId));
        order.setPrice(price);
        order.setOrderStatus(orderStatus);
        order.setFoods(foods);
        orderService = factory.getService(OrderService.class);
        orderService.save(order);
        int id = order.getClientId().getId();
        boolean isDeleted = false;
        orderService.deleteByClientId(id);
        try {
            orderService.findById(id);
        } catch (NullPointerException e) {
            isDeleted = true;
        }
        assertEquals(isDeleted, true);
    }

//    @Test(description = "Negative scenario of save method", dataProvider = "invalidDataForSave")
//    public void testSaveNegative(Integer clientId, Double price, OrderStatus orderStatus, Food food1, Food food2) throws ServiceException {
//        foods = new ArrayList<>();
//        order = new Order();
//        foods.add(food1);
//        foods.add(food2);
//        order.setClientId(new UserInfo(clientId));
//        order.setPrice(price);
//        order.setOrderStatus(orderStatus);
//        order.setFoods(foods);
//        orderService = factory.getService(OrderService.class);
//        orderService.save(order);
//        assertThrows(ServiceException.class, ()-> orderService.save(order));
//    }
}
