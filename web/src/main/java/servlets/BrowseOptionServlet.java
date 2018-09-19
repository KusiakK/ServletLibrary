package servlets;

import utility.ServletStatics;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookOption")
public class BrowseOptionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("browse").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("book-id", req.getParameter("book-id"));
        switch (req.getParameter("actionType")) {
            case "add": {
                req.getRequestDispatcher("addBook").forward(req, resp);
                break;
            }
            case "edit": {
                req.getRequestDispatcher("editBook").forward(req, resp);
                break;
            }
            case "delete": {
                req.getRequestDispatcher("deleteBook").forward(req, resp);
                break;
            }
            case "show": {
                req.getRequestDispatcher("showBook").forward(req, resp);
                break;
            }
            default: {
                req.setAttribute(ServletStatics.SINGLE_ERROR_ATTRIBUTE, "Unknown action type.");
                req.getRequestDispatcher("browse.jsp").forward(req, resp);
                break;
            }
        }
    }
}
