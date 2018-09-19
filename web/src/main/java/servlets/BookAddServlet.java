package servlets;

import models.Author;
import models.Book;
import services.AuthorService;
import services.BookService;
import utility.ErrorMessenger;
import utility.ServletStatics;

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
        try {
            int authorID = Integer.parseInt(req.getParameter("author-id"));
            author = AuthorService.getInstance().get(authorID);
        } catch (NumberFormatException e) {
            req.setAttribute(ServletStatics.SINGLE_ERROR_ATTRIBUTE, "You must pick an Author from the list");
            req.getRequestDispatcher("addBook").forward(req, resp);
        } catch (Exception e){
            req.setAttribute(ServletStatics.SINGLE_ERROR_ATTRIBUTE, "Author not found!");
            req.getRequestDispatcher("addbook").forward(req, resp);
            //TODO send redirect to servlets GET
        }


        try {
            String releaseDateString = req.getParameter("bookReleaseDate");
            releaseDate = LocalDate.parse(releaseDateString);
        } catch (NullPointerException e) {
        } catch (DateTimeParseException e) {
            req.setAttribute(ServletStatics.SINGLE_ERROR_ATTRIBUTE, "Wrong date format! ");
            req.getRequestDispatcher("addbook").forward(req, resp);
        }

        Book book = new Book();
        book.setIsbn(req.getParameter("isbn"));
        book.setReleaseDate(releaseDate);
        book.setTitle(req.getParameter("bookTitle"));
        book.setCategory(req.getParameter("bookCategory"));
        book.setAuthor(author);
        book.setSummary(req.getParameter("bookSummary"));

        List<String> errorMessages = new ArrayList<>(ErrorMessenger.getInstance().getMessages(book));

        if (!errorMessages.isEmpty()) {
            req.setAttribute(ServletStatics.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("book-add.jsp").forward(req, resp);
        }

        if (BookService.getInstance().add(book)) {
            req.setAttribute("success", "Book added to library!");
            req.getRequestDispatcher("browse").forward(req, resp);
        } else {
            req.setAttribute(ServletStatics.SINGLE_ERROR_ATTRIBUTE, "Could not save book to database");
            req.getRequestDispatcher("book-add.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("authors", AuthorService.getInstance().getAll());
        req.getRequestDispatcher("book-add.jsp").forward(req, resp);
    }
}
