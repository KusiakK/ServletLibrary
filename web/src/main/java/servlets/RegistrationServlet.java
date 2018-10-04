package servlets;

import models.User;
import services.UserService;
import utility.ErrorMessenger;
import utility.MessageUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errorMessages = new ArrayList<>();

        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        checkLoginTaken(req, resp, errorMessages, login);

        checkPasswordMatching(errorMessages, password, confirmPassword);

        User user = new User(login, password);
        errorMessages.addAll(ErrorMessenger.getInstance().getMessages(user));

        redirectIfErrors(req, resp, errorMessages);

        finalizeRegistration(req, resp, errorMessages, user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    private void checkPasswordMatching(List<String> errorMessages, String password, String confirmPassword) {
        if (null != password && null != confirmPassword && !password.equals(confirmPassword)) {
            errorMessages.add(MessageUtility.PASSWORDS_NOT_MATCHING);
        }
    }

    private void checkLoginTaken(HttpServletRequest req, HttpServletResponse resp, List<String> errorMessages, String login) throws ServletException, IOException {
        if (UserService.getInstance().isLoginTaken(login)) {
            errorMessages.add("Login is already in use!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    private void redirectIfErrors(HttpServletRequest req, HttpServletResponse resp, List<String> errorMessages) throws ServletException, IOException {
        if (!errorMessages.isEmpty()) {
            req.setAttribute(MessageUtility.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }

    private void finalizeRegistration(HttpServletRequest req, HttpServletResponse resp, List<String> errorMessages, User user) throws ServletException, IOException {
        if (null != UserService.getInstance().register(user)) {
            req.setAttribute("success", "Registration completed!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            errorMessages.add("Could not create user!");
            req.setAttribute(MessageUtility.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
