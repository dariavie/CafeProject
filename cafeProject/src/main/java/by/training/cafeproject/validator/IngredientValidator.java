package by.training.cafeproject.validator;

import by.training.cafeproject.domain.Ingredient;
import by.training.cafeproject.exception.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;

public class IngredientValidator implements Validator<Ingredient>{
    @Override
    public Ingredient validate(HttpServletRequest request) throws IncorrectFormDataException {
        Ingredient ingredient = new Ingredient();
        String parameter = request.getParameter("id");
        if (parameter != null) {
            try {
                ingredient.setId(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("id", parameter);
            }
        }
        parameter = request.getParameter("title");
        if (parameter != null && !parameter.isEmpty()) {
            ingredient.setTitle(parameter);
        } else {
            throw new IncorrectFormDataException("title", parameter);
        }
        return ingredient;
    }
}
