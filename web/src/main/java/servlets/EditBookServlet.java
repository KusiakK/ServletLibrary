package servlets;

import models.Author;
import models.Book;
import services.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet("/edit-book")
public class EditBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isbnAsString = req.getParameter("isbn");

        if (null == isbnAsString) {
            req.setAttribute("errorHead", "Missing ISBN! ");
            req.setAttribute("error", "ISBN number is required");
            req.getRequestDispatcher("book-add.jsp").forward(req, resp);
        }

        Long isbn = null;
        LocalDate releaseDate = null;

        try {
            isbn = Long.parseLong(isbnAsString);
        } catch (NumberFormatException e) {
            req.setAttribute("errorHead", "ISBN not a number! ");
            req.setAttribute("error", "ISBN has to be a number type");
            req.getRequestDispatcher("book-add.jsp").forward(req, resp);
        }

        if (null == isbn) {
            req.setAttribute("errorHead", "Something went wrong! ");
            req.getRequestDispatcher("book-add.jsp").forward(req, resp);
        }

        if (!"".equals(req.getParameter("bookReleaseDate"))) {
            try {
                releaseDate = LocalDate.parse(req.getParameter("bookReleaseDate"));
            } catch (DateTimeParseException e){
                req.setAttribute("errorHead", "Wrong date format! ");
                req.getRequestDispatcher("book-add.jsp").forward(req, resp);
            }
        }

        Book book = new Book(isbn);
        book.setReleaseDate(releaseDate);
        book.setTitle(req.getParameter("bookTitle"));
        book.setCategory(req.getParameter("bookCategory"));
        book.setAuthor(new Author(req.getParameter("author")));
        book.setSummary(req.getParameter("bookSummary"));

        if (BookService.getInstance().edit(book)) {
            req.setAttribute("successHead", "Success! ");
            req.setAttribute("success", "Book edited!");
            req.getRequestDispatcher("browse.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorHead", "Server error! ");
            req.setAttribute("error", "Could not save book to database");
            req.getRequestDispatcher("book-add.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("book-add.jsp").forward(req, resp);
    }

}
