package services;

import factories.BookFactory;
import models.Book;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private static LibraryService instance;
    private List<Book> books;

    private LibraryService() {
        books = new ArrayList<>();
    }

    public static LibraryService getInstance() {
        if (instance == null) {
            instance = new LibraryService();
        }
        return instance;
    }

    public void addBook(String title, String author) {
        Book book = BookFactory.createBook(title, author);
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book findBookByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
}
