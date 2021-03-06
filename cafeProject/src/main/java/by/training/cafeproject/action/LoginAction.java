package by.training.cafeproject.action;

import by.training.cafeproject.domain.Role;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.ServiceFactory;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.exception.ServiceException;
import by.training.cafeproject.service.impl.ServiceFactoryImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LoginAction extends Action {
    private static Logger logger = Logger.getLogger(LoginAction.class);

    private static Map<Role, List<MenuItem>> menu = new ConcurrentHashMap<>();
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources");

    static {
        menu.put(Role.WORKER, new ArrayList<>(Arrays.asList(
                new MenuItem("/search/dish/form.html", bundle.getString("menu.dishes")),
                new MenuItem("/search/ingredient/form.html", bundle.getString("menu.ingredients")),
                new MenuItem("/search/order/form.html", bundle.getString("menu.orders"))
        )));
        menu.put(Role.ADMINISTRATOR, new ArrayList<>(Arrays.asList(
                new MenuItem("/client/list.html", bundle.getString("menu.admin.clients")),
                new MenuItem("/worker/list.html", bundle.getString("menu.admin.workers"))
        )));
        menu.put(Role.CLIENT, new ArrayList<>(Arrays.asList(
                new MenuItem("/food/list.html", bundle.getString("menu.dishes")),
                new MenuItem("/order/list.html", bundle.getString("menu.orders"))
        )));
    }

    @Override
    public Set<Role> getAllowRoles() {
        return null;
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if(login != null && password != null) {
            UserService service = factory.getService(UserService.class);
            User user = service.findByLoginAndPassword(login, password);
            logger.info("user from service: " + user);
            if(user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("authorizedUser", user);
                logger.info("user: " + user);
                session.setAttribute("menu", menu.get(user.getRole()));
                session.setAttribute("title", bundle.getString("title"));
                logger.info(String.format("user \"%s\" is logged in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
                return new Forward("/index.html");
            } else {
                request.setAttribute("message", "?????? ???????????????????????? ?????? ???????????? ???? ??????????????????");
                logger.info(String.format("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
            }
        }
        return null;
    }
}
