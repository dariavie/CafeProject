package by.training.cafeproject.action.admin;

import by.training.cafeproject.domain.Role;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ClientListAction extends AdministratorAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserInfoService service = factory.getService(UserInfoService.class);
        List<UserInfo> clients1 = service.findAll();
        List<UserInfo> clients2 = new ArrayList<>();
        int size = clients1.size();
        for (int i = 0; i < size; i++) {
            if (clients1.get(i).getUserId().getRole()==Role.CLIENT) {
                clients2.add(clients1.get(i));
            }
        }
        request.setAttribute("clients", clients2);
        return null;
    }
}
