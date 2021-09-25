package by.training.cafeproject.action;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionManager defineCommand(HttpServletRequest request) {
        ActionManager current = new EmptyAction();
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            ActionEnum currentEnum = ActionEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action
                    + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
