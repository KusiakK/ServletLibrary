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

@WebServlet("/editBook")
public class BookEditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errorMessages = new ArrayList<>();

        Author author = null;

        LocalDate releaseDate = null;

        Integer pages = null;

        try {
            int authorID = Integer.parseInt(req.getParameter("author-id"));
            author = AuthorService.getInstance().get(authorID);
        } catch (Exception e) {
        }

        if (!req.getParameter("bookReleaseDate").isEmpty()) {
            try {
                String releaseDateString = req.getParameter("bookReleaseDate");
                releaseDate = LocalDate.parse(releaseDateString);
            } catch (DateTimeParseException e) {
                errorMessages.add("Wrong date format!");
            }
        }

        if (!req.getParameter("bookPages").isEmpty()) {
            try {
                pages = Integer.parseInt(req.getParameter("bookPages"));
            } catch (NumberFormatException e){
                errorMessages.add("Wrong pages format!");
            }
        }

        Book book = BookService.getInstance().get(Integer.parseInt(req.getParameter("book-id")));
        book.setIsbn(req.getParameter("isbn"));
        book.setReleaseDate(releaseDate);
        book.setTitle(req.getParameter("bookTitle"));
        book.setCategory(req.getParameter("bookCategory"));
        book.setPages(pages);
        book.setAuthor(author);
        book.setSummary(req.getParameter("bookSummary"));

        errorMessages.addAll(ErrorMessenger.getInstance().getMessages(book));

        if (!errorMessages.isEmpty()) {
            req.setAttribute("authors", AuthorService.getInstance().getAll());
            req.setAttribute("book", book);
            req.setAttribute(ServletUtility.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("book-edit.jsp").forward(req, resp);
        }

        if (BookService.getInstance().edit(book)) {
            req.setAttribute("success", "Book edited!");
            req.getRequestDispatcher("browse").forward(req, resp);
        } else {
            req.setAttribute("authors", AuthorService.getInstance().getAll());
            req.setAttribute("book", book);
            errorMessages.add("Could not save book to database");
            req.setAttribute(ServletUtility.ERROR_LIST_ATTRIBUTE, errorMessages);
            req.getRequestDispatcher("book-edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loadPage(req, resp);
        req.getRequestDispatcher("book-edit.jsp").forward(req, resp);
    }


    private void loadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = null;
        try {
            book = BookService.getInstance().get(Integer.parseInt(req.getParameter("book-id")));
        } catch (NullPointerException e) {
            req.setAttribute(ServletUtility.SINGLE_ERROR_ATTRIBUTE, "Book ID missing!");
            req.getRequestDispatcher("browse").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute(ServletUtility.SINGLE_ERROR_ATTRIBUTE, "You must pick a book to edit!");
            req.getRequestDispatcher("browse").forward(req, resp);
        }
        req.setAttribute("authors", AuthorService.getInstance().getAll());
        req.setAttribute("book", book);
    }
}
