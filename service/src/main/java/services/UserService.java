package services;

import models.User;
import repositories.UserRepository;
import exceptions.IncorrectLoginDetailsException;

public class UserService {
    private static final UserService instance = new UserService();
    private UserRepository repository;

    private UserService() {
        repository = new UserRepository();
    }

    public static UserService getInstance() {
        return instance;
    }

    public User register(User user) {
        return repository.create(user);
    }

    public Boolean login(String login, String password) throws IncorrectLoginDetailsException {
        return repository.login(login, password);
    }

    public User findUser(String login) {
        return repository.find(login);
    }

    public Boolean isLoginTaken(String login) {
        return repository.find(login) != null;
    }
 }
