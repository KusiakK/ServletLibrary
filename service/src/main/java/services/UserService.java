package services;

import repositories.UserRepository;
import utility.Static;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserService {
    private UserService instance;
    private UserRepository userRepository;

    private UserService() {
        EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory(Static.PERSISTENCE_UNIT);
        EntityManager entityManager = entityFactory.createEntityManager();
        userRepository = new UserRepository(entityManager);
    }

    public UserService getInstance() {
        if (null == instance) {
            instance = new UserService();
        }
        return instance;
    }


}
