package servlets;

import exceptions.IncorrectLoginDetailsException;
import models.User;
import services.UserService;
import utility.ServletUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errorMessages = new ArrayList<>();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Boolean userFound = false;
        try {
            userFound = UserService.getInstance().login(login, password);
        } catch (IncorrectLoginDetailsException e) {
            errorMessages.add("Incorrect Login Details!");
        } catch (Exception e) {
            errorMessages.add("Cannot log in!");
        }

        if (userFound) {
            req.getSession(true).setAttribute("login", login);
            req.setAttribute("success", "You have been logged in!");
            req.getRequestDispatcher("/home").forward(req, resp);
        } else {
            req.setAttribute(ServletUtility.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
