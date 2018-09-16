package services;

import models.Book;
import repositories.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BookService {

    private static final BookService instance = new BookService();
    private BookRepository bookRepository;

    private BookService() {
        EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityFactory.createEntityManager();
        bookRepository = new BookRepository(entityManager);
    }

    public static BookService getInstance() {
        return instance;
    }

    public Boolean add(Book book) {
        return null != bookRepository.create(book);
    }

    public Boolean edit(Book book) {
        return null != bookRepository.update(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public Book get(int id) {
        return bookRepository.read(id);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
