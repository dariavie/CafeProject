package by.training.cafeproject.action;

import javax.servlet.http.HttpServletRequest;

public class EmptyAction implements ActionManager {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
