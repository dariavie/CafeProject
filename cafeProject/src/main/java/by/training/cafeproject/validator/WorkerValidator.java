package by.training.cafeproject.validator;

import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.domain.Worker;
import by.training.cafeproject.exception.IncorrectFormDataException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;

public class WorkerValidator implements Validator<Worker> {
    @Override
    public Worker validate(HttpServletRequest request) throws IncorrectFormDataException {
        Worker worker = new Worker();
        String parameter = request.getParameter("id");
        if (parameter != null && !parameter.isEmpty()) {
            UserInfo userInfo = new UserInfo();
            try {
                userInfo.setId(Integer.parseInt(parameter));
                worker.setUserInfoId(userInfo);
            } catch (NumberFormatException e) {
                throw new IncorrectFormDataException("id", parameter);
            }
        }
        parameter = request.getParameter("startOfWork");
        if (parameter != null && !parameter.isEmpty()) {
            worker.setStartOfWork(Date.valueOf(parameter));
        } else {
            throw new IncorrectFormDataException("start of work", parameter);
        }
        parameter = request.getParameter("endOfWork");
            if (parameter != null && !parameter.isEmpty()) {
                worker.setStartOfWork(java.sql.Date.valueOf(parameter));
            }
        parameter = request.getParameter("specialization");
        if (parameter != null && !parameter.isEmpty()) {
            worker.setSpecialization(parameter);
        } else {
            throw new IncorrectFormDataException("specialization", parameter);
        }
        return worker;
    }
}
