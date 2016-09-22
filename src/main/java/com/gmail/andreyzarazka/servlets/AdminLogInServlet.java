package com.gmail.andreyzarazka.servlets;

import com.gmail.andreyzarazka.dao.ExceptionDAO;
import com.gmail.andreyzarazka.dao.FactoryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "AdminLogInServlet")
public class AdminLogInServlet extends HttpServlet {
    private static final long serialVersionUID = 2548391078153765170L;

    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private int role;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userBD = null;
        if (request.getParameter("logInBtn") != null) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            userBD = searchUser(login, password);
        }
        if (userBD == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (role == 1) {
            response.sendRedirect("/adminPage.jsp");
        } else if (role == 2) {
            response.sendRedirect("/moderator/moderatorPage.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String searchUser(String login, String password) {
        String firstName = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM meloman_db.users WHERE login = ?";
            connection = factoryDAO.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String passwordDB = resultSet.getString(3);
                if (password.equals(passwordDB)) {
                    firstName = resultSet.getString(4);
                    role = resultSet.getInt(7);
                }
            }
        } catch (SQLException | ExceptionDAO e) {
            e.printStackTrace();
        }
        return firstName;
    }
}
