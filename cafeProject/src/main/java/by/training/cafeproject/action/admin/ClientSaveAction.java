package by.training.cafeproject.action.admin;

import by.training.cafeproject.domain.Role;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.exception.IncorrectFormDataException;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.exception.ServiceException;
import by.training.cafeproject.validator.Validator;
import by.training.cafeproject.validator.ValidatorFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientSaveAction extends AdministratorAction {
    private static Logger logger = Logger.getLogger(ClientSaveAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Forward forward = new Forward("/client/edit.html");
        String locale = request.getParameter("locale");
        logger.info("locale from clientSaveAction: " + locale);
        forward.getAttributes().put("locale", locale);
        try {
            Validator<User> userValidator = ValidatorFactory.createValidator(User.class);
            User user = userValidator.validate(request);
            user.setRole(Role.CLIENT);
            logger.info("user: " + user);
            UserService userService = factory.getService(UserService.class);
            userService.save(user);
            user = userService.findByLogin(user.getLogin());
            logger.info("user2: " + user);
            Validator<UserInfo> userInfoValidator = ValidatorFactory.createValidator(UserInfo.class);
            UserInfo client = userInfoValidator.validate(request);
            client.setUserId(user);
            logger.info("client: " + client);
            UserInfoService service = factory.getService(UserInfoService.class);
            service.save(client);
            if (service.findById(client.getUserId().getId()).getId()!=null) {
                logger.info("not null userInfo: " + service.findById(client.getUserId().getId()));
                forward.getAttributes().put("id", client.getId());
                forward.getAttributes().put("message", "Данные посетителя успешно сохранены");
                logger.info(String.format("User \"%s\" saved client with identity %d", getAuthorizedUser().getLogin(), client.getUserId().getId()));
            } else {
                userService.delete(user.getId());
                forward.getAttributes().put("message", "Были обнаружены некорректные данные");
                logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save client", getAuthorizedUser().getLogin()));
            }
        } catch(IncorrectFormDataException e) {
            forward.getAttributes().put("message", "Были обнаружены некорректные данные");
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save client", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
