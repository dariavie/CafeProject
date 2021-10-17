package by.training.cafeproject.action.client;

import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.FoodIngredient;
import by.training.cafeproject.domain.Ingredient;
import by.training.cafeproject.service.FoodIngredientService;
import by.training.cafeproject.service.FoodService;
import by.training.cafeproject.service.IngredientService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class FoodListAction extends ClientAction {
    private static final Logger logger = Logger.getLogger(FoodListAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Ingredient> ingredients = new ArrayList<>();
        FoodService foodService = factory.getService(FoodService.class);
        FoodIngredientService foodIngredientService = factory.getService(FoodIngredientService.class);
        IngredientService ingredientService = factory.getService(IngredientService.class);
        List<Food> foods = foodService.findAll();
        List<FoodIngredient> foodIngredients;
        for (Food food : foods) {
            ingredients = new ArrayList<>();
            foodIngredients = foodIngredientService.findByFoodId(food.getId());
            for (FoodIngredient foodIngredient : foodIngredients) {
                ingredients.add(ingredientService.findById(foodIngredient.getIngredientId().getId()));
            }
            food.setIngredients(ingredients);
        }
        ingredients = ingredientService.findAll();
        logger.info("ingredients: " + ingredients);
        request.setAttribute("foods", foods);
        request.setAttribute("ingredients", ingredients);
        return null;
    }
}
