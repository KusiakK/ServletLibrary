package services;

import models.User;
import repositories.UserRepository;
import exceptions.IncorrectLoginDetailsException;

public class UserService {
    private static UserService instance;
    private UserRepository repository;

    private UserService() {
        repository = new UserRepository();
    }

    public static UserService getInstance() {
        if (null == instance) {
            instance = new UserService();
        }
        return instance;
    }

    public User register(User user) {
        return repository.create(user);
    }

    public Boolean login(String login, String password) throws IncorrectLoginDetailsException {
        return repository.login(login, password);
    }
}
