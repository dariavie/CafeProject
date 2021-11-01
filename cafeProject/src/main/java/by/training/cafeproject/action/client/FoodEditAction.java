package by.training.cafeproject.action.client;

import by.training.cafeproject.action.Action;
import by.training.cafeproject.domain.*;
import by.training.cafeproject.service.FoodIngredientService;
import by.training.cafeproject.service.FoodService;
import by.training.cafeproject.service.IngredientService;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class FoodEditAction extends ClientAction {
    private static final Logger logger = Logger.getLogger(FoodEditAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            IngredientService ingredientService = factory.getService(IngredientService.class);
            List<Ingredient> ingredients = ingredientService.findAll();
            if (ingredients != null) {
                request.setAttribute("ingredients", ingredients);
            }
            Integer id = (Integer)request.getAttribute("id");
            if(id == null) {
                id = Integer.parseInt(request.getParameter("id"));
            }
            FoodService service = factory.getService(FoodService.class);
            Food food = service.findById(id);
            FoodIngredientService foodIngredientService = factory.getService(FoodIngredientService.class);
            List<FoodIngredient> foodIngredients = foodIngredientService.findByFoodId(id);
            List<Ingredient> ingredientsById = new ArrayList<>();
            for (FoodIngredient foodIngredient : foodIngredients) {
                ingredientsById.add(ingredientService.findById(foodIngredient.getIngredientId().getId()));
            }
            food.setIngredients(ingredientsById);
            if(food != null) {
                request.setAttribute("food", food);
            }
        } catch(NumberFormatException e) {}

        String locale = request.getParameter("locale");
        logger.info("locale from FoodEditAction: " + locale);
        request.setAttribute("locale", locale);
        ResourceBundle bundle = ResourceBundle.getBundle("resources");
        try {
            if (locale.equals("en")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("en", "US"));
            } else if (locale.equals("ru")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("ru", "BE"));
            }
        } catch (NullPointerException e) {}
        finally {
            request.setAttribute("foodInChange", bundle.getString("food.inchange"));
            request.setAttribute("foodNew", bundle.getString("food.new"));
            request.setAttribute("foodTitlePar", bundle.getString("food.title"));
            request.setAttribute("descriptionPar", bundle.getString("description"));
            request.setAttribute("typePar", bundle.getString("type"));
            request.setAttribute("typeDish", bundle.getString("food.dish"));
            request.setAttribute("typeDrink", bundle.getString("food.drink"));
            request.setAttribute("ingredientsPar", bundle.getString("ingredients"));
            request.setAttribute("save", bundle.getString("save"));
            request.setAttribute("reset", bundle.getString("reset"));
        }
        return null;
    }
}
