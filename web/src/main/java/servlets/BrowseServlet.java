package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/browseServlet")
public class BrowseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getParameter("actionType")) {
            case "add": {
                request.getRequestDispatcher("book-add.jsp").forward(request, response);
                break;
            }
            case "edit": {
                request.getRequestDispatcher("book-edit.jsp").forward(request, response);
                break;
            }
            case "delete": {
                request.getRequestDispatcher("book-delete.jsp").forward(request, response);
                break;
            }
            case "show": {
                request.getRequestDispatcher("book-show.jsp").forward(request, response);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("browse.jsp");
    }
}
