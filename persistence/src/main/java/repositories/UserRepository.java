package repositories;

import exceptions.IncorrectLoginDetailsException;
import models.User;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
        TypedQuery<User> query = em.createQuery("SELECT user FROM User user WHERE user.login = :login", User.class);
        query.setParameter("login", login);

        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            return user;
        }
        return user;

    }
}
