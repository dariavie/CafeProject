package by.training.cafeproject.action;

import by.training.cafeproject.service.ServiceFactory;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.exception.ServiceException;
import by.training.cafeproject.service.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;

public class LoginAction implements ActionManager {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private ServiceFactory serviceFactoryObject = ServiceFactoryImpl.getInstance();
    private UserService userService = serviceFactoryObject.getUserService();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        try {
            if (userService.read(login, pass).getId()!=null) {
                request.setAttribute("user", login);
                page = ConfigurationManager.getProperty("path.page.main");
            } else {
                request.setAttribute("errorLoginPassMessage",
                        MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
        } catch (ServiceException e) {
            e.getMessage();
        } finally {
            return page;
        }
    }
}
