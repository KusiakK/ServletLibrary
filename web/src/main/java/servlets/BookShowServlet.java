package servlets;

import models.Book;
import services.BookService;
import utility.ServletUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showBook")
public class BookShowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO handle books borrowing
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = null;
        String bookIDParam = req.getParameter("book-id");
        try {
            int bookID = Integer.parseInt(bookIDParam);
            book = BookService.getInstance().get(bookID);
        } catch (NumberFormatException e) {
            req.setAttribute(ServletUtility.SINGLE_ERROR_ATTRIBUTE, "You must pick a book to show! ");
            req.getRequestDispatcher("browse").forward(req, resp);
        } catch (NullPointerException e) {
            req.setAttribute(ServletUtility.SINGLE_ERROR_ATTRIBUTE, "Book not found! ");
            req.getRequestDispatcher("browse").forward(req, resp);
        }
        req.setAttribute("book", book);
        req.getRequestDispatcher("book-show.jsp").forward(req, resp);
    }
}
