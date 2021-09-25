package by.training.cafeproject.service.impl;

import by.training.cafeproject.service.*;

public class ServiceFactoryImpl implements ServiceFactory {
    private static final ServiceFactoryImpl instance = new ServiceFactoryImpl();

    private final FoodIngredientService foodIngredientService = new FoodIngredientServiceImpl();
    private final FoodService foodService = new FoodServiceImpl();
    private final IngredientService ingredientService = new IngredientServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final RatingService ratingService = new RatingServiceImpl();
    private final UserInfoService userInfoService = new UserInfoServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final WorkerService workerService = new WorkerServiceImpl();

    private ServiceFactoryImpl() {}

    public static ServiceFactoryImpl getInstance() {
        return instance;
    }

    @Override
    public FoodIngredientService getFoodIngredientService() {
        return foodIngredientService;
    }

    @Override
    public FoodService getFoodService() {
        return foodService;
    }

    @Override
    public IngredientService getIngredientService() {
        return ingredientService;
    }

    @Override
    public OrderService getOrderService() {
        return orderService;
    }

    @Override
    public RatingService getRatingService() {
        return ratingService;
    }

    @Override
    public UserInfoService getUserInfoService() {
        return userInfoService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public WorkerService getWorkerService() {
        return workerService;
    }
}
