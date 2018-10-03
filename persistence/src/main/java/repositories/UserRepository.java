package repositories;

import models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserRepository extends GenericRepository<User, Integer> {
    public UserRepository() {
        super();
    }

    public User findByUserName(String userName) {
        Query query = em.createQuery("SELECT c FROM User c WHERE c.userName=:name");
        query.setParameter("name", userName);
        return (User) query.getSingleResult();
    }
}
