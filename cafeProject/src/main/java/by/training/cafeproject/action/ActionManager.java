package by.training.cafeproject.action;

import javax.servlet.http.HttpServletRequest;

public interface ActionManager {
    String execute(HttpServletRequest request);
}
