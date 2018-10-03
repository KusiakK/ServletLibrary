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
}
