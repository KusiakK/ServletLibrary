package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null != req.getSession(false)) {
            req.getSession(false).invalidate();
            req.setAttribute("successHead", "Success! ");
            req.setAttribute("success", "You have been logged out.");
            req.getRequestDispatcher("/home").forward(req, resp);
        }
    }
}
