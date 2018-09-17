package servlets;

import services.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/browse")
public class BrowseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("books", BookService.getInstance().getAll());
        request.getRequestDispatcher("browse.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("books", BookService.getInstance().getAll());
        request.setAttribute("books", BookService.getInstance().getAll());
        request.getRequestDispatcher("browse.jsp").forward(request, response);
    }
}
