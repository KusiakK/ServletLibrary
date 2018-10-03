package services;

import exceptions.PasswordNotMatchingException;
import models.User;
import repositories.UserRepository;
import exceptions.UserNotFoundException;

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

    public User login(String login, String password) throws UserNotFoundException, PasswordNotMatchingException {
        User user = repository.findByLogin(login);
        if (null == user) {
            throw new UserNotFoundException();
        }
        if (!user.getPassword().equals(password)) {
            throw new PasswordNotMatchingException();
        }
        return user;
    }
}
