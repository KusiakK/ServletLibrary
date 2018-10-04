package repositories;

import exceptions.IncorrectLoginDetailsException;
import models.User;

import javax.persistence.Query;

public class UserRepository extends GenericRepository<User, Integer> {
    public UserRepository() {
        super();
    }

    public Boolean login(String login, String password) throws IncorrectLoginDetailsException {
        Query query = em.createQuery("SELECT user FROM User user WHERE user.login = :login AND user.password = :pass");
        query.setParameter("login", login);
        query.setParameter("pass", password);

        try {
            query.getSingleResult();
        } catch (NullPointerException e) {
            throw new IncorrectLoginDetailsException();
        }
        return true;
    }

    public User find(String login) {
        Query query = em.createQuery("SELECT user FROM User user WHERE user.login = :login");
        query.setParameter("login", login);

        User user = null;
        try {
            query.getSingleResult();
        } catch (NullPointerException e) {
            // do nothing
        }
        return user;

    }
}
