package cli;

import java.util.Scanner;
import factory.UserFactory;
import models.User;
import services.LibraryService;
import services.UserService;

public class CommandLineInterface {
    private LibraryService libraryService;
    private UserService userService;
    private Scanner scanner;
    private User currentUser;  // Keeps track of the logged-in user

    public CommandLineInterface() {
        this.libraryService = new LibraryService();
        this.userService = new UserService();
        this.scanner = new Scanner(System.in);
        this.currentUser = null;
    }

    public void start() {
        System.out.println("Welcome to the Library Management System!");

        while (true) {
            if (currentUser == null) {
                login();
            } else {
                userOptions();
            }
        }
    }

    private void login() {
        System.out.println("\nPlease select user type to log in (Admin, Member) or type 'exit' to quit:");
        String userType = scanner.nextLine();

        if (userType.equalsIgnoreCase("exit")) {
            System.out.println("Exiting the system. Goodbye!");
            System.exit(0);  // Exit the application
        }

        currentUser = userService.loginUser(userType);  // Attempt login

        if (currentUser == null) {
            System.out.println("Login failed. Please try again.");
        }
    }

    private void logout() {
        userService.logoutUser(currentUser);
        currentUser = null;
    }

    private void userOptions() {
        System.out.println("\nLogged in as " + currentUser.getClass().getSimpleName());
        System.out.println("Options:");
        System.out.println("1. View available books");
        if (currentUser instanceof models.Admin) {
            System.out.println("2. Add a new book");
            System.out.println("3. Remove a book");
        }
        System.out.println("Type 'logout' to log out");

        String option = scanner.nextLine();
        switch (option) {
            case "1":
                libraryService.displayBooks();
                break;
            case "2":
                if (currentUser instanceof models.Admin) addBook();
                else System.out.println("Unauthorized action.");
                break;
            case "3":
                if (currentUser instanceof models.Admin) removeBook();
                else System.out.println("Unauthorized action.");
                break;
            case "logout":
                logout();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void addBook() {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter author:");
        String author = scanner.nextLine();
        libraryService.addBook(title, author);
    }

    private void removeBook() {
        System.out.println("Enter book title to remove:");
        String title = scanner.nextLine();
        libraryService.removeBook(title);
    }
}
