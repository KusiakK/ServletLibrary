package servlets;

import models.Book;
import services.BookService;
import utility.ServletStatics;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/deleteBook")
public class BookDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errorMessages = new ArrayList<>();

        if (!"confirm".equals(req.getParameter("deleteConfirmation"))) {
            resp.sendRedirect("browse");
            return;
        }

        if (null == req.getParameter("book-id")) {
            errorMessages.add("You must pick a book to delete!");
            req.setAttribute(ServletStatics.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("browse").forward(req, resp);
        }

        Integer bookID = null;

        try {
            bookID = Integer.parseInt(req.getParameter("book-id"));
        } catch (NumberFormatException e) {
            errorMessages.add("Book does not exist!");
            req.setAttribute(ServletStatics.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("browse").forward(req, resp);
        }

        Book book = BookService.getInstance().get(bookID);
        BookService.getInstance().delete(book);
        req.setAttribute("success", "Book deleted from the library.");
        req.getRequestDispatcher("browse").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getParameter("book-id")) {
            req.setAttribute(ServletStatics.SINGLE_ERROR_ATTRIBUTE, "You must pick a book to delete! ");
            req.getRequestDispatcher("browse").forward(req, resp);
        }
        req.getRequestDispatcher("book-delete.jsp").forward(req, resp);
    }
}
