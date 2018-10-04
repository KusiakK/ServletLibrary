package servlets;

import models.Book;
import services.BookService;
import utility.MessageUtility;

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

        if (!isDeleteConfirmed(req)) {
            resp.sendRedirect("browse");
            return;
        }

        redirectIfBookIdNull(req, resp, errorMessages);

        Integer bookID = getBookId(req, resp, errorMessages);

        createBookAndRedirect(req, resp, bookID);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getParameter("book-id")) {
            req.setAttribute(MessageUtility.SINGLE_ERROR_ATTRIBUTE, "You must pick a book to delete! ");
            req.getRequestDispatcher("browse").forward(req, resp);
        }
        req.getRequestDispatcher("book-delete.jsp").forward(req, resp);
    }

    private void createBookAndRedirect(HttpServletRequest req, HttpServletResponse resp, Integer bookID) throws ServletException, IOException {
        Book book = BookService.getInstance().get(bookID);
        BookService.getInstance().delete(book);
        req.setAttribute("success", "Book deleted from the library.");
        req.getRequestDispatcher("browse").forward(req, resp);
    }

    private Integer getBookId(HttpServletRequest req, HttpServletResponse resp, List<String> errorMessages) throws ServletException, IOException {
        Integer bookID = null;

        try {
            bookID = Integer.parseInt(req.getParameter("book-id"));
        } catch (NumberFormatException e) {
            errorMessages.add("Book does not exist!");
            req.setAttribute(MessageUtility.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("browse").forward(req, resp);
        }

        return bookID;
    }

    private boolean isDeleteConfirmed(HttpServletRequest req) {
        return "confirm".equals(req.getParameter("deleteConfirmation"));
    }

    private void redirectIfBookIdNull(HttpServletRequest req, HttpServletResponse resp, List<String> errorMessages) throws ServletException, IOException {
        if (null == req.getParameter("book-id")) {
            errorMessages.add("You must pick a book to delete!");
            req.setAttribute(MessageUtility.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("browse").forward(req, resp);
        }
    }
}
