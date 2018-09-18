package servlets;

import models.Book;
import services.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBook")
public class BookDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!"confirm".equals(req.getParameter("deleteConfirmation"))) {
            resp.sendRedirect("browse");
            return;
        }

            Integer bookID = null;

            try {
                bookID = Integer.parseInt(req.getParameter("book-id"));
            } catch (NumberFormatException e) {
            }

            if (null == bookID) {
                req.setAttribute("errorHead", "Error! ");
                req.setAttribute("error", "Book does not exist!");
                req.getRequestDispatcher("browse").forward(req, resp);
            }

            Book book = BookService.getInstance().get(bookID);
            BookService.getInstance().delete(book);
            req.setAttribute("successHead", "Success! ");
            req.setAttribute("success", "Book deleted from the library.");
            req.getRequestDispatcher("browse").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getParameter("book-id")){
            req.setAttribute("errorHead", "You must pick a book to delete! ");
            req.getRequestDispatcher("browse").forward(req, resp);
        }
        req.getRequestDispatcher("book-delete.jsp").forward(req, resp);
    }
}
