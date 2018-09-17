package servlets;

import models.Book;
import services.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-book")
public class BookDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if ("delete-confirm".equals(req.getParameter("deleteConfirmation"))){
            int bookID = Integer.parseInt(req.getParameter("book-id"));
            Book book = BookService.getInstance().get(bookID);
            BookService.getInstance().delete(book);
            req.setAttribute("successHead", "Success! ");
            req.setAttribute("success", "Book deleted from the library.");
            req.getRequestDispatcher("browse").forward(req, resp);
        } else {
            resp.sendRedirect("browse");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("book-delete.jsp").forward(req, resp);
    }
}
