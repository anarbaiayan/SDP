package ui;

import contexts.LibrarySearchContext;
import contexts.LibrarySortContext;
import contexts.LibraryPurchaseContext;
import models.Book;
import services.LibraryService;
import strategies.search.AuthorSearchStrategy;
import strategies.search.TitleSearchStrategy;
import strategies.sort.AuthorSortStrategy;
import strategies.sort.TitleSortStrategy;
import strategies.purchase.EBookPurchaseStrategy;
import strategies.purchase.PrintBookPurchaseStrategy;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {
    private LibraryService libraryService;
    private LibrarySearchContext searchContext;
    private LibrarySortContext sortContext;
    private LibraryPurchaseContext purchaseContext;
    private String role;  // Роль текущего пользователя ("Admin" или "User")

    public CommandLineInterface() {
        libraryService = LibraryService.getInstance();
        searchContext = new LibrarySearchContext();
        sortContext = new LibrarySortContext();
        purchaseContext = new LibraryPurchaseContext();
    }

    public void authenticateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter role (Admin/User): ");
        role = scanner.nextLine().trim();

        if (!role.equalsIgnoreCase("Admin") && !role.equalsIgnoreCase("User")) {
            System.out.println("Invalid role. Defaulting to User.");
            role = "User";
        } else {
            System.out.println("Authenticated as " + role);
        }
    }

    public void addNewBook() {
        if (!role.equalsIgnoreCase("Admin")) {
            System.out.println("Only Admin can add books.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        libraryService.addBook(title, author);
        System.out.println("Book added successfully.");
    }

    public void deleteBook() {
        if (!role.equalsIgnoreCase("Admin")) {
            System.out.println("Only Admin can delete books.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the book to delete: ");
        String title = scanner.nextLine();

        Book book = libraryService.findBookByTitle(title);
        if (book != null) {
            libraryService.getBooks().remove(book);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void searchBooksByTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter title to search: ");
        String query = scanner.nextLine();

        searchContext.setSearchStrategy(new TitleSearchStrategy());
        List<Book> results = searchContext.searchBooks(libraryService.getBooks(), query);

        results.forEach(System.out::println);
    }

    public void sortBooksByTitle() {
        sortContext.setSortStrategy(new TitleSortStrategy());
        List<Book> sortedBooks = sortContext.sortBooks(libraryService.getBooks());
        sortedBooks.forEach(System.out::println);
    }

    public void sortBooksByAuthor() {
        sortContext.setSortStrategy(new AuthorSortStrategy());
        List<Book> sortedBooks = sortContext.sortBooks(libraryService.getBooks());
        sortedBooks.forEach(System.out::println);
    }

    public void purchaseEBook(String title) {
        Book book = libraryService.findBookByTitle(title);
        if (book != null) {
            purchaseContext.setPurchaseStrategy(new EBookPurchaseStrategy());
            purchaseContext.purchaseBook(book);
        } else {
            System.out.println("Book not found.");
        }
    }

    public void purchasePrintBook(String title) {
        Book book = libraryService.findBookByTitle(title);
        if (book != null) {
            purchaseContext.setPurchaseStrategy(new PrintBookPurchaseStrategy());
            purchaseContext.purchaseBook(book);
        } else {
            System.out.println("Book not found.");
        }
    }

    public void displayAllBooks() {
        List<Book> books = libraryService.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void start() {
        authenticateUser();  // Аутентификация пользователя при запуске

        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            System.out.println("Available commands: add, delete, search, sortTitle, sortAuthor, purchaseE, purchaseP, display, exit");
            System.out.print("Enter command: ");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "add":
                    addNewBook();
                    break;
                case "delete":
                    deleteBook();
                    break;
                case "search":
                    searchBooksByTitle();
                    break;
                case "sorttitle":
                    sortBooksByTitle();
                    break;
                case "sortauthor":
                    sortBooksByAuthor();
                    break;
                case "purchasee":
                    System.out.print("Enter the title of the ebook to purchase: ");
                    String ebookTitle = scanner.nextLine();
                    purchaseEBook(ebookTitle);
                    break;
                case "purchasep":
                    System.out.print("Enter the title of the print book to purchase: ");
                    String printBookTitle = scanner.nextLine();
                    purchasePrintBook(printBookTitle);
                    break;
                case "display":
                    displayAllBooks();
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        } while (!command.equals("exit"));
    }
}
