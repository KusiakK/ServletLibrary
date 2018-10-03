package repositories;

import models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UserRepository extends GenericRepository<User, Integer> {
    public UserRepository() {
        super();
    }

    public User findByLogin(String login) {
        TypedQuery<User> query = em.createQuery("SELECT user FROM User user WHERE user.login = :login", User.class);
        query.setParameter("login", login);
//        TODO find solution for it returning null
        return query.getSingleResult();
    }
}
