package repositories;

import models.Author;

import javax.persistence.EntityManager;
import java.util.List;

public class AuthorRepository extends GenericRepository<Author, Integer>        {

    public AuthorRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Author> findAll() {
        try {
            return em.createQuery("SELECT c FROM Author c", Author.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
