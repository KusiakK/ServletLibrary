package services;

import models.Book;

public class AddBookService {

    private static final AddBookService instance = new AddBookService();

    private AddBookService() {
    }

    public static AddBookService getInstance() {
        return instance;
    }

    public Boolean add(Book book) {

        return false;
    }
}
