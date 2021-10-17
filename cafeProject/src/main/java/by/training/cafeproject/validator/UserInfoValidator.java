package by.training.cafeproject.validator;

import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.exception.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;

public class UserInfoValidator implements Validator<UserInfo>{
    @Override
    public UserInfo validate(HttpServletRequest request) throws IncorrectFormDataException {
        UserInfo userInfo = new UserInfo();
        String parameter = request.getParameter("id");
        if (parameter != null && !parameter.isEmpty()) {
//            User user = new User();
            try {
//                user.setId(Integer.parseInt(parameter));
//                userInfo.setUserId(user);
                userInfo.setId(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("id", parameter);
            }
        }
        parameter = request.getParameter("phone");
        if (parameter != null && !parameter.isEmpty()) {
            if (parameter.matches("(\\+|8)\\d{10,12}")) {
//            if (parameter.matches("^\\+\\d{1,3} \\(((\\d{2}\\) \\d{3})|(\\d{3}\\) \\d{2})|(\\d{4}\\) \\d))-\\d{2}-\\d{2}$")) {
                userInfo.setPhone(parameter);
            }
        } else {
            throw new IncorrectFormDataException("phone", parameter);
        }
        parameter = request.getParameter("email");
        if (parameter != null && !parameter.isEmpty()) {
            userInfo.setEmail(parameter);
        } else {
            throw new IncorrectFormDataException("email", parameter);
        }
        parameter = request.getParameter("name");
        if (parameter != null && !parameter.isEmpty()) {
            userInfo.setName(parameter);
        } else {
            throw new IncorrectFormDataException("name", parameter);
        }
        parameter = request.getParameter("surname");
        if (parameter != null && !parameter.isEmpty()) {
            userInfo.setSurname(parameter);
        } else {
            throw new IncorrectFormDataException("surname", parameter);
        }
//        parameter = request.getParameter("userId");
//        if (parameter != null && !parameter.isEmpty()) {
//            User user = new User();
//            try {
//                user.setId(Integer.parseInt(parameter));
//                userInfo.setUserId(user);
//            } catch (NumberFormatException e) {
//                throw new IncorrectFormDataException("userId", parameter);
//            }
//        }
        return userInfo;
    }
}
