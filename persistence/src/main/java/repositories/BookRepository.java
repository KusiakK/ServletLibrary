package repositories;

import models.Book;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BookRepository extends GenericRepository<Book, Integer> {
    public BookRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Book> findAll() {
        try {
            return em.createQuery("SELECT c FROM books c", Book.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
