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
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ClientListAction extends AdministratorAction {
    private static final Logger logger = Logger.getLogger(ClientListAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserInfoService service = factory.getService(UserInfoService.class);
        List<UserInfo> clients1 = service.findAll();
        List<UserInfo> clients2 = new ArrayList<>();
        int size = clients1.size();
        for (int i = 0; i < size; i++) {
            if (clients1.get(i).getUserId().getRole()==Role.CLIENT) {
                clients2.add(clients1.get(i));
            }
        }
        String locale = request.getParameter("locale");
        request.setAttribute("locale", locale);
        logger.info("locale from ClientListAction: " + locale);
        ResourceBundle bundle = ResourceBundle.getBundle("resources");
        try {
            if (locale.equals("en")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("en", "US"));
            } else if (locale.equals("ru")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("ru", "BE"));
            }
        } catch (NullPointerException e) {}
          finally {
            request.setAttribute("list", bundle.getString("admin.client.list"));
            request.setAttribute("login", bundle.getString("login"));
            request.setAttribute("password", bundle.getString("password"));
            request.setAttribute("surname", bundle.getString("surname"));
            request.setAttribute("name", bundle.getString("name"));
            request.setAttribute("phone", bundle.getString("phone"));
            request.setAttribute("email", bundle.getString("email"));
            request.setAttribute("remove", bundle.getString("admin.client.remove"));
            request.setAttribute("change", bundle.getString("admin.client.change"));
            request.setAttribute("add", bundle.getString("admin.client.add"));

            request.setAttribute("clients", clients2);
            return null;
        }
    }
}
