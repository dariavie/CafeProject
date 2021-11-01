package by.training.cafeproject.action.admin;

import by.training.cafeproject.domain.Role;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClientEditAction extends AdministratorAction {
    private static final Logger logger = Logger.getLogger(ClientEditAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            Integer id = (Integer)request.getAttribute("id");
            if(id == null) {
                id = Integer.parseInt(request.getParameter("id"));
            }
            UserInfoService service = factory.getService(UserInfoService.class);
            UserInfo client = service.findById(id);
            logger.info("client: " + client);
            if (client != null && client.getUserId().getRole().equals(Role.CLIENT)) {
                request.setAttribute("client", client);
            }
        } catch(NumberFormatException e) {}

        String locale = request.getParameter("locale");
        logger.info("locale from clientEditAction: " + locale);
        request.setAttribute("locale", locale);
        ResourceBundle bundle = ResourceBundle.getBundle("resources");
        try {
            if (locale.equals("en")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("en", "US"));
            } else if (locale.equals("ru")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("ru", "BE"));
            }
        } catch (NullPointerException e) {}
        finally {
            request.setAttribute("clientInChange", bundle.getString("admin.client.inchange"));
            request.setAttribute("clientNew", bundle.getString("admin.client.new"));
            request.setAttribute("loginPar", bundle.getString("login"));
            request.setAttribute("passwordPar", bundle.getString("password"));
            request.setAttribute("surnamePar", bundle.getString("surname"));
            request.setAttribute("namePar", bundle.getString("name"));
            request.setAttribute("phonePar", bundle.getString("phone"));
            request.setAttribute("emailPar", bundle.getString("email"));
            request.setAttribute("save", bundle.getString("save"));
            request.setAttribute("reset", bundle.getString("reset"));
        }
        return null;
    }
}
