package by.training.cafeproject.service;

public interface ServiceFactory {
    FoodIngredientService getFoodIngredientService();

    FoodService getFoodService();

    IngredientService getIngredientService();

    OrderService getOrderService();

    RatingService getRatingService();

    UserInfoService getUserInfoService();

    UserService getUserService();

    WorkerService getWorkerService();
}
