package by.training.cafeproject.action.admin;

import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientDeleteAction extends AdministratorAction {
    private static Logger logger = Logger.getLogger(ClientDeleteAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Forward forward = new Forward("/client/list.html");
        String locale = request.getParameter("locale");
        forward.getAttributes().put("locale", locale);
        try {
            UserService userService = factory.getService(UserService.class);
            UserInfoService userInfoService = factory.getService(UserInfoService.class);
            String email = request.getParameter("email");
            logger.info("email from request: " + email);
            Integer id = userInfoService.findByEmail(email).getId();
            userInfoService.delete(id);
            userService.delete(id);
            forward.getAttributes().put("message", "Посетитель успешно удалён");
            logger.info(String.format("User \"%s\" deleted client with identity %d", getAuthorizedUser().getLogin(), id));
        } catch(NumberFormatException e) {
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to delete client", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
