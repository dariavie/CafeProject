package by.training.cafeproject.validator;

import by.training.cafeproject.domain.User;
import by.training.cafeproject.exception.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;

public class UserValidator implements Validator<User> {
    @Override
    public User validate(HttpServletRequest request) throws IncorrectFormDataException {
        User user = new User();
        String parameter = request.getParameter("id");
        if (parameter != null) {
            try {
                user.setId(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("id", parameter);
            }
        }
        parameter = request.getParameter("login");
        if(parameter != null && !parameter.isEmpty()) {
            user.setLogin(parameter);
        } else {
            throw new IncorrectFormDataException("login", parameter);
        }
        parameter = request.getParameter("password");
        if(parameter != null && !parameter.isEmpty()) {
            user.setPassword(parameter);
        } else {
            throw new IncorrectFormDataException("password", parameter);
        }
        return user;
    }
}
