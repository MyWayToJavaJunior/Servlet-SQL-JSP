package com.gmail.andreyzarazka.servlets;

import com.gmail.andreyzarazka.dao.ExceptionDAO;
import com.gmail.andreyzarazka.dao.FactoryDAO;
import com.gmail.andreyzarazka.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminViewServlet", urlPatterns = "/view")
public class AdminViewServlet extends HttpServlet {
    private static final long serialVersionUID = 4709581287874012817L;

    private FactoryDAO factoryDAO = FactoryDAO.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> users = factoryDAO.getUserDAO().getAll();
            request.setAttribute("users", users);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/adminPage.jsp");
            dispatcher.forward(request, response);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
    }
}
