package servlets;

import models.Book;
import models.User;
import services.BookService;
import services.UserService;
import utility.MessageUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/borrowBook")
public class BookBorrowServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = getBookFromParam(req, resp);
        borrowBook(req, resp, book);

        req.setAttribute("book", book);
        req.getRequestDispatcher("showBook").forward(req, resp);
    }

    private void borrowBook(HttpServletRequest req, HttpServletResponse resp, Book book) throws ServletException, IOException {
        HttpSession session;
        String login;
        if ((session = req.getSession(false)) != null && (login = (String) session.getAttribute("login")) != null){
            User user = UserService.getInstance().findUser(login);
            book.borrow(user);
        } else {
            req.setAttribute(MessageUtility.SINGLE_ERROR_ATTRIBUTE, "You have to log in first! ");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    //    TODO extract to servlet helper class
    private Book getBookFromParam(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = null;
        String bookIDParam = req.getParameter("book-id");
        try {
            int bookID = Integer.parseInt(bookIDParam);
            book = BookService.getInstance().get(bookID);
        } catch (NumberFormatException e) {
            req.setAttribute(MessageUtility.SINGLE_ERROR_ATTRIBUTE, "You must pick a book to show! ");
            req.getRequestDispatcher("browse").forward(req, resp);
        } catch (NullPointerException e) {
            req.setAttribute(MessageUtility.SINGLE_ERROR_ATTRIBUTE, "Book not found! ");
            req.getRequestDispatcher("browse").forward(req, resp);
        }
        return book;
    }
}
