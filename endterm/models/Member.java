package models;

public class Member implements User {
    @Override
    public void accessLibrary() {
        System.out.println("Member has limited access to the library.");
    }
}
