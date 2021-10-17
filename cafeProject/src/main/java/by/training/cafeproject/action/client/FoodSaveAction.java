package by.training.cafeproject.action.client;

import by.training.cafeproject.action.Action;
import by.training.cafeproject.domain.*;
import by.training.cafeproject.exception.IncorrectFormDataException;
import by.training.cafeproject.service.FoodService;
import by.training.cafeproject.service.IngredientService;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.exception.ServiceException;
import by.training.cafeproject.validator.Validator;
import by.training.cafeproject.validator.ValidatorFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class FoodSaveAction extends ClientAction {
    private static final Logger logger = Logger.getLogger(FoodSaveAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Forward forward = new Forward("/food/edit.html");
        try {
            Validator<Food> foodValidator = ValidatorFactory.createValidator(Food.class);
            Food food = foodValidator.validate(request);
            food.setPrice(1.0);
            food.setRecipe(null);
            FoodService foodService = factory.getService(FoodService.class);
            IngredientService ingredientService = factory.getService(IngredientService.class);
            String[] parameters = request.getParameterValues("ingredients");
            List<String> ingredientsTitles = Arrays.asList(parameters);
            List<Ingredient> ingredients = new ArrayList<>();
            for (String title : ingredientsTitles) {
                ingredients.add(ingredientService.findByTitle(title));
            }
            food.setIngredients(ingredients);
            logger.info("food from save: " + food);
            foodService.save(food);
        } catch(IncorrectFormDataException e) {
            forward.getAttributes().put("message", "Были обнаружены некорректные данные");
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save food", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
