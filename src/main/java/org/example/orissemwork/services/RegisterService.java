package org.example.orissemwork.services;

import org.example.orissemwork.db.UserDAO;
import org.example.orissemwork.model.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RegisterService {
    public static final String EMAIL_REG = "[A-z0-9_-]+@[A-z0-9_-]+.[a-z]+";
    public static final String USERNAME_REG = "^[a-zA-Z0-9_.]+$";
    public static final String PWD_REG = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+{};':|,.<>/?]).{8,}$";

    public static boolean usernameExist( User account ) {return UserDAO.getByUsername(account.getUsername()) != null; }

    public static boolean emailExist( User account ) { return UserDAO.getByEmail(account.getEmail()) != null; }

    public static boolean valuesIsValid(User account, HttpServletRequest req, String repeatPassword) {
        if (!check(account, req)) { return false; }

        if (usernameExist(account)) {
            req.setAttribute("error", "User with this username is already registered");
            return false;
        } else if (emailExist(account)) {
            req.setAttribute("error", "User with this email is already registered");
            return false;
        } else if (!account.getPassword().equals(repeatPassword)) {
            req.setAttribute("error", "Passwords don't match");
            return false;
        } else {
            return true;
        }
    }

    public static void save(User account) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(account.getPassword());
        account.setPassword(password);

        UserDAO.saveToDB(account);
    }

    public static boolean check(User account, HttpServletRequest req) {
        if (!account.getEmail().matches(EMAIL_REG)) {
            req.setAttribute("error", "Invalid email");
            return false;
        } else if (!account.getUsername().matches(USERNAME_REG)) {
            req.setAttribute("error", "Invalid username");
            return false;
        } else if (!account.getPassword().matches(PWD_REG)) {
            req.setAttribute("error", "Invalid password");
            return false;
        } else {
            return true;
        }
    }
}