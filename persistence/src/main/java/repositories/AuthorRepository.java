package repositories;

import models.Author;

import javax.persistence.EntityManager;
import java.math.BigInteger;

public class AuthorRepository extends GenericRepository<Author, BigInteger> {

    public AuthorRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
