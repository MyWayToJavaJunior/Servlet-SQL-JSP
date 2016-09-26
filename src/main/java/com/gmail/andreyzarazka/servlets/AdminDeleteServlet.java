package com.gmail.andreyzarazka.servlets;

import com.gmail.andreyzarazka.dao.ExceptionDAO;
import com.gmail.andreyzarazka.dao.FactoryDAO;
import com.gmail.andreyzarazka.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class AdminDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 5381813833086159614L;

    private FactoryDAO factoryDAO = FactoryDAO.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> mass = request.getParameterNames();
        try {
            UserDAO userDAO = factoryDAO.getUserDAO();
            int id = Integer.parseInt(mass.nextElement());
            System.out.println("servlet user id" + id);
            userDAO.delete(id);
            response.sendRedirect("/admin-panel");
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
    }
}
