package by.training.cafeproject.action;

import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.service.ServiceFactory;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.exception.ServiceException;
import by.training.cafeproject.service.impl.ServiceFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegistrationAction implements ActionManager {
    private static final Logger registrationActionLogger = LogManager.getLogger(RegistrationAction.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_SURNAME = "surname";
    private static final String PARAM_NAME_PHONE = "phone";
    private static final String PARAM_NAME_EMAIL = "email";
    private ServiceFactory serviceFactoryObject = ServiceFactoryImpl.getInstance();
    private UserService userService = serviceFactoryObject.getUserService();
    private UserInfoService userInfoService = serviceFactoryObject.getUserInfoService();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String name = request.getParameter(PARAM_NAME_NAME);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        String phone = request.getParameter(PARAM_NAME_PHONE);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        int i = 6;

        try{
            if (userService.readByLogin(login).getId()==null) {
                registrationActionLogger.info("1");
                if (userInfoService.readByPhone(phone)==null) {
                    registrationActionLogger.info("2");
                    if (userInfoService.readByEmail(email)==null) {
                        registrationActionLogger.info("3");
                        User user = new User();
                        registrationActionLogger.info("user creation");
                        user.setId(i);
                        registrationActionLogger.info("id");
                        user.setLogin(login);
                        registrationActionLogger.info("login");
                        user.setPassword(password);
                        registrationActionLogger.info("pass");
                        user.setRole(2);
                        registrationActionLogger.info("role");
                        userService.create(user);
                        registrationActionLogger.info("4");

                        UserInfo userInfo = new UserInfo();
                        userInfo.setUserId(userService.read(login, password));
                        userInfo.setName(name);
                        userInfo.setSurname(surname);
                        userInfo.setPhone(phone);
                        userInfo.setEmail(email);
                        userInfoService.create(userInfo);
                        registrationActionLogger.info("5");
                        request.setAttribute("user", login);
                        registrationActionLogger.info("6");
                        page = ConfigurationManager.getProperty("path.page.main");
                        registrationActionLogger.info("7");
                        i++;
                    } else {
                        request.setAttribute("errorEmailExistingMessage",
                                MessageManager.getProperty("message.existingemail"));
                        page = ConfigurationManager.getProperty("path.page.registration");
                    }
                } else {
                    request.setAttribute("errorPhoneExistingMessage",
                            MessageManager.getProperty("message.existingphone"));
                    page = ConfigurationManager.getProperty("path.page.registration");
                }
            } else {
                request.setAttribute("errorLoginExistingMessage",
                        MessageManager.getProperty("message.existinglogin"));
                page = ConfigurationManager.getProperty("path.page.registration");
            }
        } catch (ServiceException e) {
            e.getMessage();
        } finally {
            return page;
        }
    }
}
