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
        List<String> errorMessages = new ArrayList<>();

        Book book = (Book) req.getAttribute("book");
        if (null == book) {
            book = new Book();
        }

        Author author = null;

        LocalDate releaseDate = null;

//        try {
//            int authorID = Integer.parseInt(req.getParameter("author-id"));
//            author = AuthorService.getInstance().get(authorID);
//        } catch (NumberFormatException e) {
//            errorMessages.add("You must pick an Author from the list");
//        } catch (Exception e) {
//            errorMessages.add("Author not found!");
//        }

        if (!req.getParameter("bookReleaseDate").isEmpty()) {
            try {
                String releaseDateString = req.getParameter("bookReleaseDate");
                releaseDate = LocalDate.parse(releaseDateString);
            } catch (NullPointerException e) {
            } catch (DateTimeParseException e) {
                errorMessages.add("Wrong date format! ");
            }
        }

        book.setIsbn(req.getParameter("isbn"));
        book.setReleaseDate(releaseDate);
        book.setTitle(req.getParameter("bookTitle"));
        book.setCategory(req.getParameter("bookCategory"));
        book.setAuthor(author);
        book.setSummary(req.getParameter("bookSummary"));

        errorMessages.addAll(ErrorMessenger.getInstance().getMessages(book));

        if (!errorMessages.isEmpty()) {
            req.setAttribute("authors", AuthorService.getInstance().getAll());
            req.setAttribute("book", book);
            req.setAttribute(ServletStatics.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("book-add.jsp").forward(req, resp);
        }

        if (BookService.getInstance().add(book)) {
            req.setAttribute("success", "Book added to library!");
            req.getRequestDispatcher("browse").forward(req, resp);
        } else {
            req.setAttribute("authors", AuthorService.getInstance().getAll());
            req.setAttribute("book", book);
            errorMessages.add("Could not save book to database");
            req.setAttribute(ServletStatics.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("book-add.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("authors", AuthorService.getInstance().getAll());
        req.getRequestDispatcher("book-add.jsp").forward(req, resp);
    }
}
