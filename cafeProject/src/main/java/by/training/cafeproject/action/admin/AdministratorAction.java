package by.training.cafeproject.action.admin;

import by.training.cafeproject.action.Action;
import by.training.cafeproject.domain.Role;
import by.training.cafeproject.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class AdministratorAction extends Action {
    public AdministratorAction() {
        getAllowRoles().add(Role.ADMINISTRATOR);
    }
}
