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

    @Override
    public FoodIngredientService getFoodIngredientService() {
        return null;
    }

    @Override
    public FoodService getFoodService() {
        return null;
    }

    @Override
    public IngredientService getIngredientService() {
        return null;
    }

    @Override
    public OrderService getOrderService() {
        return null;
    }

    @Override
    public RatingService getRatingService() {
        return null;
    }

    @Override
    public UserInfoService getUserInfoService() {
        return null;
    }

    @Override
    public UserService getUserService() {
        return null;
    }

    @Override
    public WorkerService getWorkerService() {
        return null;
    }
}
