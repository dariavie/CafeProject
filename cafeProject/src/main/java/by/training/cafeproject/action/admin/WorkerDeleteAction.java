package by.training.cafeproject.action.admin;

import by.training.cafeproject.service.UserInfoService;
import by.training.cafeproject.service.UserService;
import by.training.cafeproject.service.WorkerService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WorkerDeleteAction extends AdministratorAction {
    private static final Logger logger = Logger.getLogger(WorkerDeleteAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Forward forward = new Forward("/worker/list.html");
        try {
            UserService userService = factory.getService(UserService.class);
            UserInfoService userInfoService = factory.getService(UserInfoService.class);
            WorkerService service = factory.getService(WorkerService.class);
            Integer id = Integer.parseInt(request.getParameter("id"));
            service.delete(id);
            userInfoService.delete(id);
            userService.delete(id);
            forward.getAttributes().put("message", "Сотрудник успешно удалён");
            logger.info(String.format("User \"%s\" deleted worker with identity %d", getAuthorizedUser().getLogin(), id));
        } catch(NumberFormatException e) {
            logger.warn(String.format("Incorrect data was found when user \"%s\" tried to delete worker", getAuthorizedUser().getLogin()), e);
        }
        return forward;
    }
}
