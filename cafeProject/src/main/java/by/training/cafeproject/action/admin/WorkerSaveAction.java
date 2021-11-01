package by.training.cafeproject.action.admin;

import by.training.cafeproject.domain.Role;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.domain.Worker;
import by.training.cafeproject.exception.IncorrectFormDataException;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.WorkerService;
import by.training.cafeproject.service.exception.ServiceException;
import by.training.cafeproject.validator.Validator;
import by.training.cafeproject.validator.ValidatorFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WorkerSaveAction extends AdministratorAction {
    private static final Logger logger = Logger.getLogger(WorkerSaveAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Forward forward = new Forward("/worker/edit.html");
        String locale = request.getParameter("locale");
        forward.getAttributes().put("locale", locale);
        try {
            Validator<User> userValidator = ValidatorFactory.createValidator(User.class);
            User user = userValidator.validate(request);
            user.setRole(Role.WORKER);
            logger.info("user: " + user);
            UserService userService = factory.getService(UserService.class);
            userService.save(user);
            user = userService.findByLogin(user.getLogin());
            logger.info("user2: " + user);
            Validator<UserInfo> userInfoValidator = ValidatorFactory.createValidator(UserInfo.class);
            UserInfo userInfo = userInfoValidator.validate(request);
            userInfo.setUserId(user);
            logger.info("userInfo: " + userInfo);
            UserInfoService userInfoService = factory.getService(UserInfoService.class);
            userInfoService.save(userInfo);
            logger.info("client2: " + userInfo);
            if (userInfoService.findById(userInfo.getUserId().getId()).getId()!=null) {
                Validator<Worker> validator = ValidatorFactory.createValidator(Worker.class);
                Worker worker = validator.validate(request);
                logger.info("worker after validator: " + worker);
                worker.setUserInfoId(userInfo);
                logger.info("worker before creation in userInfoService: " + worker);
                logger.info("worker id: " + worker.getId());
                WorkerService workerService = factory.getService(WorkerService.class);
                workerService.save(worker);
                if (workerService.findById(worker.getUserInfoId().getId())!=null) {
                    forward.getAttributes().put("id", worker.getId());
                    forward.getAttributes().put("message", "Данные сотрудника успешно сохранены");
                    logger.info(String.format("User \"%s\" saved worker with identity %d", getAuthorizedUser().getLogin(), worker.getId()));
                } else {
                    userInfoService.delete(userInfo.getId());
                    userService.delete(user.getId());
                    forward.getAttributes().put("message", "Были обнаружены некорректные данные");
                    logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save userInfo", getAuthorizedUser().getLogin()));
                }
            } else {
                userService.delete(user.getId());
                forward.getAttributes().put("message", "Были обнаружены некорректные данные");
                logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save userInfo", getAuthorizedUser().getLogin()));
            }
        } catch(IncorrectFormDataException e) {
            forward.getAttributes().put("message", "Были обнаружены некорректные данные");
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save user", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
