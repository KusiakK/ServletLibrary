package servlets;

import exceptions.PasswordNotMatchingException;
import exceptions.UserNotFoundException;
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


        String userName = req.getParameter("userName");
        String password = req.getParameter("password");


        User user = null;
        try {
            user = UserService.getInstance().login(userName, password);
        } catch (UserNotFoundException e) {
            errorMessages.add("User name not found!");
        } catch (PasswordNotMatchingException e) {
            errorMessages.add("Wrong Password!");
        } catch (Exception e) {
            errorMessages.add("Unknown Error!");
        }

        if (null != user) {
            req.getSession(true).setAttribute("userName", user.getUserName());
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
