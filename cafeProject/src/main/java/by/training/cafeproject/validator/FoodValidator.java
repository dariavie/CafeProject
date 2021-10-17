package by.training.cafeproject.validator;

import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.FoodType;
import by.training.cafeproject.domain.Ingredient;
import by.training.cafeproject.exception.IncorrectFormDataException;
import by.training.cafeproject.service.IngredientService;
import com.sun.webkit.dom.HTMLOptionsCollectionImpl;
import org.w3c.dom.html.HTMLCollection;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodValidator implements Validator<Food> {
    @Override
    public Food validate(HttpServletRequest request) throws IncorrectFormDataException {
        Food food = new Food();
        String parameter = request.getParameter("id");
        if (parameter != null) {
            try {
                food.setId(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("id", parameter);
            }
        }
        parameter = request.getParameter("foodTitle");
        if (parameter != null && !parameter.isEmpty()) {
            food.setTitle(parameter);
        }  else {
            throw new IncorrectFormDataException("title", parameter);
        }
        parameter = request.getParameter("type");
        if (parameter != null && !parameter.isEmpty()) {
            if (parameter.equals("блюдо")) {
                food.setType(FoodType.DISH);
            } else if (parameter.equals("напиток")) {
                food.setType(FoodType.DRINK);
            }
        } else {
            throw new IncorrectFormDataException("type", parameter);
        }
        parameter = request.getParameter("description");
        if (parameter != null && !parameter.isEmpty()) {
            food.setDescription(parameter);
        } else {
            throw new IncorrectFormDataException("type", parameter);
        }
        return food;
    }
}
