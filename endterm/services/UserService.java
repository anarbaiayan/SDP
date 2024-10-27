package services;

import models.User;
import factory.UserFactory;

public class UserService {
    public User loginUser(String userType) {
        try {
            User user = UserFactory.createUser(userType);
            System.out.println("Successfully logged in as " + userType);
            return user;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid user type: " + userType);
            return null;
        }
    }

    public void logoutUser(User user) {
        if (user != null) {
            System.out.println("Logging out " + user.getClass().getSimpleName());
        } else {
            System.out.println("No user to log out.");
        }
    }
}
