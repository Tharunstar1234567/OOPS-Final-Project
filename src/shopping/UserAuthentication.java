package shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserAuthentication {
    private List<User> users;

    public UserAuthentication() {
        this.users = new ArrayList<>();
    }

    public void registerUser(String username, String password, String email) {
        if (isUsernameTaken(username)) {
            System.out.println("Username is already taken. Please choose a different one.");
            return;
        }

        if (!isValidUsername(username)) {
            System.out.println("Invalid username. Username must contain only lowercase letters.");
            return;
        }

        if (!isValidPassword(password)) {
            System.out.println("Invalid password. Password must contain one uppercase letter, one special character, one number, and at least 8 characters.");
            return;
        }

        if (!isValidEmail(email)) {
            System.out.println("Invalid email. Email must contain the @ symbol.");
            return;
        }

        User newUser = new User(username, password, email);
        users.add(newUser);
        System.out.println("User registered successfully!");
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        System.out.println("Login failed. Please check your credentials.");
        return null;
    }

    private boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidUsername(String username) {
        // Username contains all lowercase letters
        return Pattern.matches("^[a-z]+$", username);
    }

    private static boolean isValidPassword(String password) {
        // Password contains one uppercase, one special character, one number, and all lowercase
        return Pattern.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*()])(?=.*[0-9])(?=.*[a-z]).{8,}$", password);
    }

    private static boolean isValidEmail(String email) {
        // Email contains the @ symbol
        return email.contains("@");
    }
}
