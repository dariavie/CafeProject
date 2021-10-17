package by.training.cafeproject.validator;

import by.training.cafeproject.domain.*;

import java.util.HashMap;
import java.util.Map;

public class ValidatorFactory {
    private static Map<Class<? extends Entity>, Class<? extends Validator<?>>> validators = new HashMap<>();

    static {
        validators.put(FoodIngredient.class, FoodIngredientValidator.class);
        validators.put(Food.class, FoodValidator.class);
        validators.put(Ingredient.class, IngredientValidator.class);
        validators.put(Order.class, OrderValidator.class);
        validators.put(User.class, UserValidator.class);
        validators.put(UserInfo.class, UserInfoValidator.class);
        validators.put(Worker.class, WorkerValidator.class);
    }

    @SuppressWarnings("unchecked")
    public static <Type extends Entity> Validator<Type> createValidator(Class<Type> entity) {
        try {
            return (Validator<Type>)validators.get(entity).newInstance();
        } catch(InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}
