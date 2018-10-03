package servlets;

import models.Author;
import models.Book;
import services.AuthorService;
import services.BookService;
import utility.ErrorMessenger;
import utility.ServletUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addBook")
public class BookAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errorMessages = new ArrayList<>();

        Author author = getAuthor(req);
        LocalDate releaseDate = getLocalDate(req, errorMessages);
        Integer pages = getPages(req, errorMessages);

        Book book = assembleBook(req, author, releaseDate, pages);

        errorMessages.addAll(ErrorMessenger.getInstance().getMessages(book));

        redirectIfErrors(req, resp, errorMessages, book);

        createBookAndRedirect(req, resp, errorMessages, book);
    }

    private void createBookAndRedirect(HttpServletRequest req, HttpServletResponse resp, List<String> errorMessages, Book book) throws ServletException, IOException {
        if (BookService.getInstance().add(book)) {
            req.setAttribute("success", "Book added to library!");
            req.getRequestDispatcher("browse").forward(req, resp);
        } else {
            req.setAttribute("authors", AuthorService.getInstance().getAll());
            req.setAttribute("book", book);
            errorMessages.add("Could not save book to database");
            req.setAttribute(ServletUtility.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("book-add.jsp").forward(req, resp);
        }
    }

    private void redirectIfErrors(HttpServletRequest req, HttpServletResponse resp, List<String> errorMessages, Book book) throws ServletException, IOException {
        if (!errorMessages.isEmpty()) {
            req.setAttribute("authors", AuthorService.getInstance().getAll());
            req.setAttribute("book", book);
            req.setAttribute(ServletUtility.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("book-add.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("authors", AuthorService.getInstance().getAll());
        req.getRequestDispatcher("book-add.jsp").forward(req, resp);
    }


    private Book assembleBook(HttpServletRequest req, Author author, LocalDate releaseDate, Integer pages) {
        Book book = (Book) req.getAttribute("book");
        if (null == book) {
            book = new Book();
        }

        book.setIsbn(req.getParameter("isbn"));
        book.setReleaseDate(releaseDate);
        book.setTitle(req.getParameter("bookTitle"));
        book.setCategory(req.getParameter("bookCategory"));
        book.setPages(pages);
        book.setAuthor(author);
        book.setSummary(req.getParameter("bookSummary"));

        return book;
    }

    private Integer getPages(HttpServletRequest req, List<String> errorMessages) {
        Integer pages = null;
        if (!req.getParameter("bookPages").isEmpty()) {
            try {
                pages = Integer.parseInt(req.getParameter("bookPages"));
            } catch (NumberFormatException e) {
                errorMessages.add("Wrong pages format!");
            }
        }
        return pages;
    }

    private Author getAuthor(HttpServletRequest req) {
        Author author = null;
        int authorID = Integer.parseInt(req.getParameter("author-id"));
        author = AuthorService.getInstance().get(authorID);
        return author;
    }

    private LocalDate getLocalDate(HttpServletRequest req, List<String> errorMessages) {
        LocalDate releaseDate = null;
        if (!req.getParameter("bookReleaseDate").isEmpty()) {
            try {
                String releaseDateParam = req.getParameter("bookReleaseDate");
                releaseDate = LocalDate.parse(releaseDateParam);
            } catch (DateTimeParseException e) {
                errorMessages.add("Wrong date format! ");
            }
        }
        return releaseDate;
    }
}
