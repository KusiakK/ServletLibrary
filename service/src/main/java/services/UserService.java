package services;

import models.User;
import repositories.UserRepository;
import utility.Static;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserService {
    private UserService instance;
    private UserRepository userRepository;

    private UserService() {
        userRepository = new UserRepository();
    }

    public UserService getInstance() {
        if (null == instance) {
            instance = new UserService();
        }
        return instance;
    }

    public Boolean register(User user) {
        return null != userRepository.create(user);
    }
}
