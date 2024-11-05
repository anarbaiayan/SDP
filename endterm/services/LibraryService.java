package services;

import models.Book;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private static LibraryService instance;
    private List<Book> books;

    private LibraryService() {
        books = new ArrayList<>();
        initializeLibrary();
    }

    public static LibraryService getInstance() {
        if (instance == null) {
            instance = new LibraryService();
        }
        return instance;
    }

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
    }

    public Book findBookByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public List<Book> getBooks() {
        return books;
    }

    private void initializeLibrary() {
        books.add(new Book("1984", "George Orwell"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book("Pride and Prejudice", "Jane Austen"));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger"));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book("One Piece Vol. 1", "Eiichiro Oda"));
        books.add(new Book("Naruto Vol. 1", "Masashi Kishimoto"));
        books.add(new Book("Attack on Titan Vol. 1", "Hajime Isayama"));
        books.add(new Book("Death Note Vol. 1", "Tsugumi Ohba"));
        books.add(new Book("Dragon Ball Vol. 1", "Akira Toriyama"));
    }
}
