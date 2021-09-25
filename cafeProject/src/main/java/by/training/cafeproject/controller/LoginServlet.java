////package by.training.cafeproject.controller;
//
//import by.training.cafeproject.domain.User;
//import by.training.cafeproject.service.ServiceFactory;
//import by.training.cafeproject.service.UserService;
//import by.training.cafeproject.service.exception.ServiceException;
//import by.training.cafeproject.service.impl.ServiceFactoryImpl;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
////@WebServlet(name = "login-page", value = "/login-page")
////public class LoginServlet extends HttpServlet {
////
////    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        response.setContentType("text/html");
////
////        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
////
////        dispatcher.forward(request, response);
////    }
////
////    @Override
////    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        try {
////            ServiceFactory serviceFactoryObject = ServiceFactoryImpl.getInstance();
////            UserService userService = serviceFactoryObject.getUserService();
////            String login = req.getParameter("login");
////            String password = req.getParameter("password");
////            User user = userService.read(login, password);
////
////            if (user == null) {
////                String errorMessage = "Invalid userName or Password";
////
////                req.setAttribute("errorMessage", errorMessage);
////
////                RequestDispatcher dispatcher //
////                        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
////
////                dispatcher.forward(req, resp);
////                return;
////            }
//
////            AppUtils.storeLoginedUser(request.getSession(), userAccount);
////
////            //
////            int redirectId = -1;
////            try {
////                redirectId = Integer.parseInt(request.getParameter("redirectId"));
////            } catch (Exception e) {
////            }
////            String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
////            if (requestUri != null) {
////                response.sendRedirect(requestUri);
////            } else {
////                // По умолчанию после успешного входа в систему
////                // перенаправить на страницу /userInfo
//////                response.sendRedirect(request.getContextPath() + "/userInfo");
//////            }
////        } catch (ServiceException e) {
////            e.getMessage();
////        }
////    }
////
////    public void destroy() {
////    }
////}
//
