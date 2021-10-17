package by.training.cafeproject.action.admin;

import by.training.cafeproject.domain.Role;
import by.training.cafeproject.domain.Worker;
import by.training.cafeproject.service.WorkerService;
import by.training.cafeproject.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WorkerEditAction extends AdministratorAction {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
//        request.setAttribute("roles", Role.values());
        try {
            Integer id = (Integer)request.getAttribute("id");
            if(id == null) {
                id = Integer.parseInt(request.getParameter("id"));
            }
            WorkerService service = factory.getService(WorkerService.class);
            Worker worker = service.findById(id);
            if(worker != null) {
                request.setAttribute("worker", worker);
            }
        } catch(NumberFormatException e) {}
        return null;
    }
}
