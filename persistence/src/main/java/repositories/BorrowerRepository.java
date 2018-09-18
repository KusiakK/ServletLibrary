package repositories;

import models.User;

import javax.persistence.EntityManager;

public class BorrowerRepository extends GenericRepository<User, Integer> {
    protected BorrowerRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
