package repositories;

import models.Book;

import javax.persistence.EntityManager;
import java.math.BigInteger;

public class BookRepository extends GenericRepository<Book, Integer> {
    public BookRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
