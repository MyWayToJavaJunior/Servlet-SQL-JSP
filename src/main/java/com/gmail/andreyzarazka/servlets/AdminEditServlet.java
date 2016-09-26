package com.gmail.andreyzarazka.servlets;

import com.gmail.andreyzarazka.dao.*;
import com.gmail.andreyzarazka.domain.Address;
import com.gmail.andreyzarazka.domain.MusicType;
import com.gmail.andreyzarazka.domain.Role;
import com.gmail.andreyzarazka.domain.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class AdminEditServlet extends HttpServlet {
    private static final long serialVersionUID = -582494122275711814L;
    private static Logger log = Logger.getLogger(AdminEditServlet.class.getName());


    private FactoryDAO factoryDAO = FactoryDAO.getInstance();
    private int id;

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
            } catch (ExceptionDAO e) {
                log.error("Cannot read list musics", e);
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
            user.setId(id);
            userDAO.update(user);
        } catch (ExceptionDAO e) {
            log.error("Cannot udate user", e);
        }
        response.sendRedirect("/admin-panel");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> mass = request.getParameterNames();
        id = Integer.parseInt(mass.nextElement());
        try {
            UserDAO userDAO = factoryDAO.getUserDAO();
            RoleDAO rolesUtils = factoryDAO.getRoleDAO();
            MusicTypeDAO musicTypeDAO = factoryDAO.getMusicTypeDAO();

            User user = userDAO.getByFullUserId(id);
            Role roleUser = user.getRole();
            List<MusicType> musicTypes = musicTypeDAO.getAll();

            request.setAttribute("user", user);
            request.setAttribute("roleUser", roleUser);
            request.setAttribute("roles", rolesUtils.getAll());
            request.setAttribute("musicTypesUser", user.getMusicTypes());
            request.setAttribute("musicTypes", musicTypes);
        } catch (ExceptionDAO e) {
            log.error("Cannot read", e);
        }
        request.getRequestDispatcher("pages/adminEdit.jsp").forward(request, response);
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
