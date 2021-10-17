package by.training.cafeproject.validator;

import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.FoodIngredient;
import by.training.cafeproject.domain.Ingredient;
import by.training.cafeproject.exception.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;

public class FoodIngredientValidator implements Validator<FoodIngredient> {
    @Override
    public FoodIngredient validate(HttpServletRequest request) throws IncorrectFormDataException {
        FoodIngredient foodIngredient = new FoodIngredient();
        String parameter = request.getParameter("id");
        if (parameter != null) {
            try {
                foodIngredient.setId(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("id", parameter);
            }
        }
        parameter = request.getParameter("foodId");
        if (parameter != null && !parameter.isEmpty()) {
            Food food = new Food();
            try {
                food.setId(Integer.parseInt(parameter));
                foodIngredient.setFoodId(food);
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("food id", parameter);
            }
        }
        parameter = request.getParameter("ingredientId");
        if (parameter != null && !parameter.isEmpty()) {
            Ingredient ingredient = new Ingredient();
            try {
                ingredient.setId(Integer.parseInt(parameter));
                foodIngredient.setIngredientId(ingredient);
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("food id", parameter);
            }
        }
        parameter = request.getParameter("ingredientAmount");
        if (parameter != null && !parameter.isEmpty()) {
            foodIngredient.setIngredientAmount(parameter);
        } else {
            throw new IncorrectFormDataException("ingredient amount", parameter);
        }
        return foodIngredient;
    }
}
