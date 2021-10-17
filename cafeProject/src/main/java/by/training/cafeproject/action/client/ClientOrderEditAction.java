package by.training.cafeproject.action.client;

import by.training.cafeproject.domain.*;
import by.training.cafeproject.service.FoodService;
import by.training.cafeproject.service.OrderFoodService;
import by.training.cafeproject.service.OrderService;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.exception.ServiceException;
import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ClientOrderEditAction extends ClientAction {
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
        return null;
    }
}
