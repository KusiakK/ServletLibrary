package servlets;

import models.Author;
import models.Book;
import services.AuthorService;
import services.BookService;
import utility.ErrorMessenger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet("/addBook")
public class BookAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author author = null;
        LocalDate releaseDate = null;
        List<String> errorMessages = new ArrayList<>();
        try {
            author = AuthorService.getInstance().get(Integer.parseInt(req.getParameter("author-id")));
        } catch (NumberFormatException e) {
            errorMessages.add("Cannot get Author!");
            req.getRequestDispatcher("book-add.jsp").forward(req, resp);
        }

        if (!"".equals(req.getParameter("bookReleaseDate"))) {
            try {
                releaseDate = LocalDate.parse(req.getParameter("bookReleaseDate"));
            } catch (DateTimeParseException e) {
                errorMessages.add("Wrong date format!");
                req.getRequestDispatcher("book-add.jsp").forward(req, resp);
            }
        }

        Book book = new Book();
        book.setIsbn(req.getParameter("isbn"));
        book.setReleaseDate(releaseDate);
        book.setTitle(req.getParameter("bookTitle"));
        book.setCategory(req.getParameter("bookCategory"));
        book.setAuthor(author);
        book.setSummary(req.getParameter("bookSummary"));

        errorMessages.addAll(ErrorMessenger.getInstance().getMessages(book));

        if (!errorMessages.isEmpty()) {
            req.setAttribute("errors", errorMessages);
            req.getRequestDispatcher("book-add.jsp").forward(req, resp);
        }

        if (BookService.getInstance().add(book)) {
            req.setAttribute("success", "Success! Book added to library!");
            req.getRequestDispatcher("browse").forward(req, resp);
        } else {
            errorMessages.add("Could not save book to database");
            req.setAttribute("errors", errorMessages);
            req.getRequestDispatcher("book-add.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("authors", AuthorService.getInstance().getAll());
        req.getRequestDispatcher("book-add.jsp").forward(req, resp);
    }
}
