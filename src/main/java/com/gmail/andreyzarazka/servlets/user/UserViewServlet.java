package com.gmail.andreyzarazka.servlets.user;

import com.gmail.andreyzarazka.dao.*;
import com.gmail.andreyzarazka.domain.Address;
import com.gmail.andreyzarazka.domain.MusicType;
import com.gmail.andreyzarazka.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserViewServlet extends HttpServlet {

    private static final long serialVersionUID = 659655983802357349L;

    private FactoryDAO factoryDAO = com.gmail.andreyzarazka.dao.FactoryDAO.getInstance();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = factoryDAO.getUserDAO();
        List<User> users = null;
        try {
            users = userDAO.getAllFullUsers();
            request.setAttribute("users", users);

            request.getRequestDispatcher("pages/userPage.jsp").forward(request, response);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
    }
}
