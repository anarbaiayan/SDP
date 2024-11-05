package factory;

import models.Admin;
import models.Member;
import models.User;

import java.util.Objects;

public class UserFactory {
    public static User createUser(String userType) {
        if(Objects.equals(userType, "Admin")) {
            return new Admin();
        }else if(Objects.equals(userType, "Member")) {
            return new Member();
        }else{
            throw new IllegalArgumentException("Unknown user type");
        }
    }
}
