package by.training.cafeproject.validator;

import by.training.cafeproject.domain.*;
import by.training.cafeproject.exception.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;

public class OrderValidator implements Validator<Order> {
    @Override
    public Order validate(HttpServletRequest request) throws IncorrectFormDataException {
        Order order = new Order();
        String parameter = request.getParameter("id");
        if (parameter != null) {
            try {
                order.setId(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("id", parameter);
            }
        }
//        parameter = request.getParameter("clientId");
//        if (parameter != null && !parameter.isEmpty()) {
//            User client = new User();
//            try {
//                client.setId(Integer.parseInt(parameter));
//                order.setClientId(client);
//            } catch (NumberFormatException e) {
//                throw new IncorrectFormDataException("client id", parameter);
//            }
//        }
//        parameter = request.getParameter("foodId");
//        if (parameter != null && !parameter.isEmpty()) {
//            Food food = new Food();
//            try {
//                food.setId(Integer.parseInt(parameter));
//                order.setFoodId(food);
//            } catch (NumberFormatException e) {
//                throw new IncorrectFormDataException("food id", parameter);
//            }
//        }
        return order;
    }
}
