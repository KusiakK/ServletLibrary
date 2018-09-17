package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/browse-option")
public class BrowseOptionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("browse").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getParameter("actionType")) {
            case "add": {
                request.getRequestDispatcher("add-book").forward(request, response);
                break;
            }
            case "edit": {
                request.getRequestDispatcher("edit-book").forward(request, response);
                break;
            }
            case "delete": {
                request.getRequestDispatcher("delete-book").forward(request, response);
                break;
            }
            case "show": {
                request.getRequestDispatcher("show-book").forward(request, response);
                break;
            }
            default: {
                request.setAttribute("errorHead", "Error! ");
                request.setAttribute("error", "Unknown action type.");
                request.getRequestDispatcher("browse.jsp").forward(request, response);
                break;
            }
        }
    }
}
