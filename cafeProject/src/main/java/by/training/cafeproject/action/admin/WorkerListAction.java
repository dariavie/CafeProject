package by.training.cafeproject.action.admin;

import by.training.cafeproject.domain.Worker;
import by.training.cafeproject.service.WorkerService;
import by.training.cafeproject.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class WorkerListAction extends AdministratorAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        WorkerService service = factory.getService(WorkerService.class);
        List<Worker> workers = service.findAll();
        request.setAttribute("workers", workers);
        return null;
    }
}
