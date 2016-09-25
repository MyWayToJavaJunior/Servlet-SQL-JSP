package com.gmail.andreyzarazka.servlets;

import com.gmail.andreyzarazka.dao.*;
import com.gmail.andreyzarazka.domain.Address;
import com.gmail.andreyzarazka.domain.MusicType;
import com.gmail.andreyzarazka.domain.Role;
import com.gmail.andreyzarazka.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class AdminCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 7208599399177986204L;

    private FactoryDAO factoryDAO = FactoryDAO.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        Address addressUser = new Address();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String age = request.getParameter("age");
        String role = request.getParameter("userRole");
        String country = request.getParameter("country");
        String street = request.getParameter("street");
        String zipCode = request.getParameter("zipCode");
        String[] musicTypes = request.getParameterValues("listMusics");

        if (checkLogin(login)) {
            request.setAttribute("login", "Login already used!");
        }

        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(Integer.parseInt(age));
        user.setRoleId(Integer.parseInt(role));
        addressUser.setCountry(country);
        addressUser.setStreet(street);
        addressUser.setZipCode(Integer.parseInt(zipCode));
        user.setAddress(addressUser);

        if (musicTypes != null) {
            List<MusicType> setMusicTypes = null;
            try {
                setMusicTypes = factoryDAO.getMusicTypeDAO().getAll();
            } catch (ExceptionDAO exceptionDAO) {
                exceptionDAO.printStackTrace();
            }
            Iterator<MusicType> iterator = setMusicTypes.iterator();
            while (iterator.hasNext()) {
                MusicType music = iterator.next();
                if (searchMusicType(musicTypes, music)) {
                } else {
                    iterator.remove();
                }
            }
            user.setMusicTypes(setMusicTypes);
        }

        try {
            UserDAO userDAO = factoryDAO.getUserDAO();
            userDAO.add(user);
            response.sendRedirect("/admin-panel");
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RoleDAO roleDAO = factoryDAO.getRoleDAO();
            List<Role> roles = roleDAO.getAll();
            request.setAttribute("roles", roles);

            MusicTypeDAO musicTypeDAO = factoryDAO.getMusicTypeDAO();
            List<MusicType> musicTypes = musicTypeDAO.getAll();
            request.setAttribute("musicTypes", musicTypes);

            request.getRequestDispatcher("pages/adminCreate.jsp").forward(request, response);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
    }

    private boolean checkLogin(String login) {
        boolean used = false;
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
                if (!(resultSet.getString(2) == null)) {
                    used = true;
                }
            }
        } catch (SQLException | ExceptionDAO e) {
            e.printStackTrace();
        }
        return used;
    }

    private boolean searchMusicType(String[] musicTypes, MusicType musicType) {
        boolean count = false;
        for (int i = 0; i < musicTypes.length; i++) {
            if (musicType.getMusicTypeName().equals(musicTypes[i])) {
                count = true;
            }
        }
        return count;
    }
}
