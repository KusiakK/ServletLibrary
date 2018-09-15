package repositories;

import models.Author;

import javax.persistence.EntityManager;

public class AuthorRepository extends GenericRepository<Author, Integer> {

    public AuthorRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
