package services;

import models.Author;
import repositories.AuthorRepository;
import utility.Static;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AuthorService {

    private static AuthorService instance;
    private AuthorRepository authorRepository;

    private AuthorService() {
        EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory(Static.PERSISTENCE_UNIT);
        EntityManager entityManager = entityFactory.createEntityManager();
        authorRepository = new AuthorRepository(entityManager);
    }

    public static AuthorService getInstance() {
        if (null == instance) {
            instance = new AuthorService();
        }
        return instance;
    }

    public Boolean add(Author author) {
        return null != authorRepository.create(author);
    }

    public Boolean edit(Author author) {
        return null != authorRepository.update(author);
    }

    public void delete(Author author) {
        authorRepository.delete(author);
    }

    public Author get(int id) {
        return authorRepository.read(id);
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }
}
