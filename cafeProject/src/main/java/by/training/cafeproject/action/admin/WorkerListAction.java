package by.training.cafeproject.action.admin;

import by.training.cafeproject.domain.Worker;
import by.training.cafeproject.service.WorkerService;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class WorkerListAction extends AdministratorAction {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(WorkerListAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        WorkerService service = factory.getService(WorkerService.class);
        List<Worker> workers = service.findAll();
        request.setAttribute("workers", workers);
        String locale = request.getParameter("locale");
        request.setAttribute("locale", locale);
        logger.info("locale from WorkerListAction: " + locale);
        ResourceBundle bundle = ResourceBundle.getBundle("resources");
        try {
            if (locale.equals("en")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("en", "US"));
            } else if (locale.equals("ru")) {
                bundle = ResourceBundle.getBundle("resources", new Locale("ru", "BE"));
            }
        } catch (NullPointerException e) {}
        finally {
            request.setAttribute("list", bundle.getString("admin.worker.list"));
            request.setAttribute("login", bundle.getString("login"));
            request.setAttribute("password", bundle.getString("password"));
            request.setAttribute("surname", bundle.getString("surname"));
            request.setAttribute("name", bundle.getString("name"));
            request.setAttribute("phone", bundle.getString("phone"));
            request.setAttribute("email", bundle.getString("email"));
            request.setAttribute("startDate", bundle.getString("admin.worker.startdate"));
            request.setAttribute("endDate", bundle.getString("admin.worker.enddate"));
            request.setAttribute("specialization", bundle.getString("admin.worker.specialization"));
            request.setAttribute("remove", bundle.getString("admin.worker.remove"));
            request.setAttribute("change", bundle.getString("admin.worker.change"));
            request.setAttribute("add", bundle.getString("admin.worker.add"));
        }
        return null;
    }
}
