package by.training.cafeproject.action.client;

import by.training.cafeproject.domain.*;
import by.training.cafeproject.service.FoodService;
import by.training.cafeproject.service.OrderFoodService;
import by.training.cafeproject.service.OrderService;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.exception.ServiceException;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClientOrderEditAction extends ClientAction {
    private static final Logger logger = Logger.getLogger(ClientOrderEditAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            FoodService foodService = factory.getService(FoodService.class);
            List<Food> foods = foodService.findAll();
            if (foods!=null) {
                request.setAttribute("foods", foods);
            }
            Integer id = (Integer)request.getAttribute("id");
            if(id == null) {
                id = Integer.parseInt(request.getParameter("id"));
            }
            OrderService orderService = factory.getService(OrderService.class);
            Order order = orderService.findById(id);
            OrderFoodService orderFoodService = factory.getService(OrderFoodService.class);
            List<OrderFood> orderFoods = orderFoodService.findByOrderId(id);
            ArrayList<Food> foodsById = new ArrayList<>();
            for (OrderFood orderFood : orderFoods) {
                foodsById.add(foodService.findById(orderFood.getFoodId().getId()));
            }
            order.setFoods(foodsById);
            if (order != null) {
                request.setAttribute("order", order);
            }
        } catch(NumberFormatException e) {}

        String locale = request.getParameter("locale");
        logger.info("locale from FoodEditAction: " + locale);
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
            request.setAttribute("orderInChange", bundle.getString("order.inchange"));
            request.setAttribute("orderNew", bundle.getString("order.new"));
            request.setAttribute("foodChoose", bundle.getString("food.choose"));
            request.setAttribute("foodCreate", bundle.getString("food.create"));
            request.setAttribute("save", bundle.getString("save"));
            request.setAttribute("reset", bundle.getString("reset"));
        }
        return null;
    }
}
