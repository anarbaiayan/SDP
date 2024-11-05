package services;

import java.util.ArrayList;
import java.util.List;
import models.Book;

public class LibraryService {
    private List<Book> books;

    public LibraryService() {
        books = new ArrayList<>();
        books.add(new Book("One Piece", "Eichiro Oda"));
    }

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added: " + title);
    }

    public void removeBook(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        System.out.println("Book removed: " + title);
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("Available books:");
            books.forEach(System.out::println);
        }
    }
}
