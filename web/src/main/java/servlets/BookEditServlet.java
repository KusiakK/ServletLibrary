package servlets;

import models.Author;
import models.Book;
import services.AuthorService;
import services.BookService;
import utility.ErrorMessenger;
import utility.MessageUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/editBook")
public class BookEditServlet extends BookAddServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errorMessages = new ArrayList<>();

        Author author = getAuthor(req);
        LocalDate releaseDate = getDate(req, errorMessages);
        Integer pages = getPages(req, errorMessages);

        Book book = assembleBook(req, author, releaseDate, pages);

        errorMessages.addAll(ErrorMessenger.getInstance().getMessages(book));

        redirectIfErrors(req, resp, errorMessages, book);

        createBookAndRedirect(req, resp, errorMessages, book);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loadPage(req, resp);
        req.getRequestDispatcher("book-edit.jsp").forward(req, resp);
    }

    @Override
    protected Book assembleBook(HttpServletRequest req, Author author, LocalDate releaseDate, Integer pages) {
        Book book = BookService.getInstance().get(Integer.parseInt(req.getParameter("book-id")));
        book.setIsbn(req.getParameter("isbn"));
        book.setReleaseDate(releaseDate);
        book.setTitle(req.getParameter("bookTitle"));
        book.setCategory(req.getParameter("bookCategory"));
        book.setPages(pages);
        book.setAuthor(author);
        book.setSummary(req.getParameter("bookSummary"));
        return book;
    }

    private void loadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = null;
        try {
            book = BookService.getInstance().get(Integer.parseInt(req.getParameter("book-id")));
        } catch (NullPointerException e) {
            req.setAttribute(MessageUtility.SINGLE_ERROR_ATTRIBUTE, "Book ID missing!");
            req.getRequestDispatcher("browse").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute(MessageUtility.SINGLE_ERROR_ATTRIBUTE, "You must pick a book to edit!");
            req.getRequestDispatcher("browse").forward(req, resp);
        }
        req.setAttribute("authors", AuthorService.getInstance().getAll());
        req.setAttribute("book", book);
    }
}
