package com.gmail.andreyzarazka.servlets.moderator;

import com.gmail.andreyzarazka.dao.*;
import com.gmail.andreyzarazka.domain.Address;
import com.gmail.andreyzarazka.domain.MusicType;
import com.gmail.andreyzarazka.domain.User;
import com.gmail.andreyzarazka.servlets.AdminStartServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class ModeratorPageServlet extends HttpServlet {
    private static final long serialVersionUID = -4998928168592087511L;
    private static Logger log = Logger.getLogger(ModeratorPageServlet.class.getName());

    private FactoryDAO factoryDAO = FactoryDAO.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            UserDAO userDAO = factoryDAO.getUserDAO();
            List<User> users = userDAO.getAllFullUsers();
            request.setAttribute("users", users);

            AddressDAO addressDAO = factoryDAO.getAddressDAO();
            List<Address> addresses = addressDAO.getAll();
            request.setAttribute("addresses", addresses);

            MusicTypeDAO musicTypeDAO = factoryDAO.getMusicTypeDAO();
            List<MusicType> musicTypes = musicTypeDAO.getAll();
            request.setAttribute("musicTypes", musicTypes);

        } catch (ExceptionDAO e) {
            log.error("Cannot read", e);
        }
        request.getRequestDispatcher("pages/moderatorPage.jsp").forward(request, response);
    }
}
