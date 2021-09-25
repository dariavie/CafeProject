package by.training.cafeproject.controller;

import by.training.cafeproject.action.ActionManager;
import by.training.cafeproject.action.ActionFactory;
import by.training.cafeproject.action.ConfigurationManager;
import by.training.cafeproject.action.MessageManager;
import by.training.cafeproject.dao.pool.ConnectionPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void  processRequest(HttpServletRequest request,
                                 HttpServletResponse response)
            throws ServletException, IOException {
//        Connection connection = null;
//        try {
//            Context initCtx = new InitialContext();
//            Context envCtx = (Context) initCtx.lookup("java:comp/env");
//            DataSource ds = (DataSource) envCtx.lookup("jdbc/cafe_db");
//            connection = ds.getConnection();
//        } catch (Exception e) {
//            e.getMessage();
//        }
        String page = null;
        ActionFactory client =  new ActionFactory();
        ActionManager command = client.defineCommand(request);
        page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.getMessage();
//        }
    }
}
