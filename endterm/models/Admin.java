package models;

public class Admin implements User {
    @Override
    public void accessLibrary() {
        System.out.println("Admin has full access to the library.");
    }
}
