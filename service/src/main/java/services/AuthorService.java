package services;

import models.Author;
import repositories.AuthorRepository;

import java.util.List;

public class AuthorService {

    private static final AuthorService instance = new AuthorService();
    private AuthorRepository authorRepository;

    private AuthorService() {
        authorRepository = new AuthorRepository();
    }

    public static AuthorService getInstance() {
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
