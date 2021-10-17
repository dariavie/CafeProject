package by.training.cafeproject.action.admin;

import by.training.cafeproject.domain.Role;
import by.training.cafeproject.domain.User;
import by.training.cafeproject.domain.UserInfo;
import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientEditAction extends AdministratorAction {
    private static final Logger logger = Logger.getLogger(ClientEditAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            Integer id = (Integer)request.getAttribute("id");
            if(id == null) {
                id = Integer.parseInt(request.getParameter("id"));
            }
            UserInfoService service = factory.getService(UserInfoService.class);
            UserInfo client = service.findById(id);
            logger.info("client: " + client);
            if(client != null && client.getUserId().getRole().equals(Role.CLIENT)) {
                request.setAttribute("client", client);
            }
        } catch(NumberFormatException e) {}
        return null;
    }
}
