package by.training.cafeproject.controller;

import by.training.cafeproject.action.*;
import by.training.cafeproject.action.admin.*;
import by.training.cafeproject.action.client.*;
import by.training.cafeproject.domain.Food;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionFromUriFilter implements Filter {

    private static Logger logger = Logger.getLogger(ActionFromUriFilter.class);

    private static Map<String, Class<? extends Action>> actions = new ConcurrentHashMap<>();

    static {
        actions.put("/", MainAction.class);
        actions.put("/index", MainAction.class);
        actions.put("/login", LoginAction.class);
        actions.put("/logout", LogoutAction.class);

        actions.put("/profile/edit", ProfileEditAction.class);
        actions.put("/profile/save", ProfileSaveAction.class);

        actions.put("/client/list", ClientListAction.class);
        actions.put("/client/edit", ClientEditAction.class);
        actions.put("/client/save", ClientSaveAction.class);
        actions.put("/client/delete", ClientDeleteAction.class);

        actions.put("/worker/list", WorkerListAction.class);
        actions.put("/worker/edit", WorkerEditAction.class);
        actions.put("/worker/save", WorkerSaveAction.class);
        actions.put("/worker/delete", WorkerDeleteAction.class);

        actions.put("/food/list", FoodListAction.class);
        actions.put("/food/edit", FoodEditAction.class);
        actions.put("/food/save", FoodSaveAction.class);

        actions.put("/order/list", OrderListAction.class);
        actions.put("/order/edit", ClientOrderEditAction.class);
        actions.put("/order/save", OrderSaveAction.class);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();
            logger.debug(String.format("Starting of processing of request for URI \"%s\"", uri));
            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf('.');
            String actionName;
            if(endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName = uri.substring(beginAction);
            }
            Class<? extends Action> actionClass = actions.get(actionName);
            try {
                Action action = actionClass.newInstance();
                action.setName(actionName);
                logger.info("actionName: " + actionName);
                httpRequest.setAttribute("action", action);
                chain.doFilter(request, response);
            } catch (InstantiationException | IllegalAccessException | NullPointerException e) {
                logger.error("It is impossible to create action handler object", e);
                httpRequest.setAttribute("error", String.format("Запрошенный адрес %s не может быть обработан сервером", uri));
                httpRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        } else {
            logger.error("It is impossible to use HTTP filter");
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {}
}
