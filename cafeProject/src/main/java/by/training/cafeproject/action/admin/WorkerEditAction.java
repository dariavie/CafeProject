package by.training.cafeproject.action.admin;

import by.training.cafeproject.domain.Role;
import by.training.cafeproject.domain.Worker;
import by.training.cafeproject.service.WorkerService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

public class WorkerEditAction extends AdministratorAction {
    private static final Logger logger = Logger.getLogger(WorkerEditAction.class);

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

        String locale = request.getParameter("locale");
        logger.info("locale from WorkerEditAction: " + locale);
        request.setAttribute("locale", locale);
        ResourceBundle bundle = ResourceBundle.getBundle("resources");
        try {
            if (locale.equals("en")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("en", "US"));
            } else if (locale.equals("ru")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("ru", "BE"));
            }
        } catch (NullPointerException e) {}
        finally {
            request.setAttribute("workerInChange", bundle.getString("admin.client.inchange"));
            request.setAttribute("workerNew", bundle.getString("admin.client.new"));
            request.setAttribute("loginPar", bundle.getString("login"));
            request.setAttribute("passwordPar", bundle.getString("password"));
            request.setAttribute("surnamePar", bundle.getString("surname"));
            request.setAttribute("namePar", bundle.getString("name"));
            request.setAttribute("phonePar", bundle.getString("phone"));
            request.setAttribute("emailPar", bundle.getString("email"));
            request.setAttribute("startDatePar", bundle.getString("admin.worker.startdate"));
            request.setAttribute("endDatePar", bundle.getString("admin.worker.enddate"));
            request.setAttribute("specializationPar", bundle.getString("admin.worker.specialization"));
            request.setAttribute("save", bundle.getString("save"));
            request.setAttribute("reset", bundle.getString("reset"));
        }
        return null;
    }
}
