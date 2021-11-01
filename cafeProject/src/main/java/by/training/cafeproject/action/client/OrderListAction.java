package by.training.cafeproject.action.client;

import by.training.cafeproject.action.Action;
import by.training.cafeproject.domain.Food;
import by.training.cafeproject.domain.Order;
import by.training.cafeproject.domain.OrderFood;
import by.training.cafeproject.service.FoodService;
import by.training.cafeproject.service.OrderFoodService;
import by.training.cafeproject.service.OrderService;
import by.training.cafeproject.service.exception.ServiceException;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class OrderListAction extends ClientAction {
    private static final Logger logger = Logger.getLogger(OrderListAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        OrderService orderService = factory.getService(OrderService.class);
        logger.info("1: " + OrderListAction.super.getAuthorizedUser().getId());
        List<Order> orders = orderService.findByClientId(OrderListAction.super.getAuthorizedUser().getId());
        request.setAttribute("orders", orders);

        String locale = request.getParameter("locale");
        request.setAttribute("locale", locale);
        logger.info("locale from OrderListAction: " + locale);
        ResourceBundle bundle = ResourceBundle.getBundle("resources");
        try {
            if (locale.equals("en")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("en", "US"));
            } else if (locale.equals("ru")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("ru", "BE"));
            }
        } catch (NullPointerException e) {}
        finally {
            request.setAttribute("list", bundle.getString("client.order.list"));
            request.setAttribute("name", bundle.getString("name"));
            request.setAttribute("foods", bundle.getString("foods"));
            request.setAttribute("price", bundle.getString("price"));
            request.setAttribute("status", bundle.getString("status"));
            request.setAttribute("add", bundle.getString("client.order.add"));
        }
        return null;
    }
}
