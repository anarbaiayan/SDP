
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

    public CommandLineInterface() {
        libraryService = LibraryService.getInstance();
        searchContext = new LibrarySearchContext();
        sortContext = new LibrarySortContext();
        purchaseContext = new LibraryPurchaseContext();
    }

    public void addNewBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        libraryService.addBook(title, author);
        System.out.println("Book added successfully.");
    }

    public void searchBooksByTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter title to search: ");
        String query = scanner.nextLine();

        searchContext.setSearchStrategy(new TitleSearchStrategy());
        List<Book> results = searchContext.searchBooks(libraryService.getBooks(), query);

        results.forEach(System.out::println);
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
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            System.out.println("Available commands: add, search, sort, purchaseE, purchaseP, display, exit");
            System.out.print("Enter command: ");
            command = scanner.nextLine().trim().toLowerCase();


            switch (command) {
                case "add":
                    addNewBook();
                    break;
                case "search":
                    searchBooksByTitle();
                    break;
                case "sort":
                    sortBooksByAuthor();
                    break;
                case "purchaseE":
                    System.out.print("Enter the title of the ebook to purchase: ");
                    String ebookTitle = scanner.nextLine();
                    purchaseEBook(ebookTitle);
                    break;
                case "purchaseP":
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
