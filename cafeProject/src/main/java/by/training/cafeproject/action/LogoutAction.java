package by.training.cafeproject.action;

import javax.servlet.http.HttpServletRequest;

public class LogoutAction implements ActionManager {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        request.getSession().invalidate();
        return page;
    }
}
