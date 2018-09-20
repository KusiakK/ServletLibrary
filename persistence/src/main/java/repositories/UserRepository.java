package repositories;

import models.User;

import javax.persistence.EntityManager;

public class UserRepository extends GenericRepository<User, Integer> {
    public UserRepository() {
        super();
    }
}
