package repositories;

import models.Borrower;

import javax.persistence.EntityManager;
import java.math.BigInteger;

public class BorrowerRepository extends GenericRepository<Borrower, Integer> {
    protected BorrowerRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
