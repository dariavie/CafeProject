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
import java.util.Locale;
import java.util.ResourceBundle;

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
        String loc = request.getParameter("locale");
        logger.info("locale: " + loc);
        request.setAttribute("foods", foods);
        request.setAttribute("ingredients", ingredients);

        String locale = request.getParameter("locale");
        request.setAttribute("locale", locale);
        logger.info("locale from FoodListAction: " + locale);
        ResourceBundle bundle = ResourceBundle.getBundle("resources");
        try {
            if (locale.equals("en")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("en", "US"));
            } else if (locale.equals("ru")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("ru", "BE"));
            }
        } catch (NullPointerException e) {}
        finally {
            request.setAttribute("list", bundle.getString("client.food.list"));
            request.setAttribute("foodTitle", bundle.getString("food.title"));
            request.setAttribute("description", bundle.getString("description"));
            request.setAttribute("price", bundle.getString("price"));
            request.setAttribute("type", bundle.getString("type"));
            request.setAttribute("ingredients", bundle.getString("ingredients"));
            request.setAttribute("add", bundle.getString("client.food.add"));
        }
        return null;
    }
}
