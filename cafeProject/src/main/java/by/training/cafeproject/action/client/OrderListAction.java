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

public class OrderListAction extends ClientAction {
    private static final Logger logger = Logger.getLogger(OrderListAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        OrderService orderService = factory.getService(OrderService.class);
//        OrderFoodService orderFoodService = factory.getService(OrderFoodService.class);
//        FoodService foodService = factory.getService(FoodService.class);
//        List<OrderFood> orderFoods = new ArrayList<>();
//        ArrayList<Food> foods = new ArrayList<>();

        logger.info("1: " + OrderListAction.super.getAuthorizedUser().getId());
        List<Order> orders = orderService.findByClientId(OrderListAction.super.getAuthorizedUser().getId());
//        logger.info("orders: " + orders);
//        for (Order order : orders) {
//            foods = new ArrayList<>();
//            logger.info("orderId: " + order.getId());
//            orderFoods = orderFoodService.findByOrderId(order.getId());
//            for (OrderFood orderFood : orderFoods) {
//                foods.add(foodService.findById(orderFood.getFoodId().getId()));
//            }
//            order.setFoods(foods);
//        }
        request.setAttribute("orders", orders);
        return null;
    }
}
