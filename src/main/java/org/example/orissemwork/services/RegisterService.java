package org.example.orissemwork.services;

import org.example.orissemwork.db.WorkWithDBForUser;
import org.example.orissemwork.model.User;

import javax.servlet.http.HttpServletRequest;

public class RegisterService {
    private static final String EMAIL_REG = "[A-z0-9_-]+@[A-z0-9_-]+.[a-z]+";
    private static final String USERNAME_REG = "^[a-zA-Z0-9]+$";
    private static final String PWD_REG = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+-{};':|,.<>/?]).{8,}$";

    public static boolean usernameExist( User account ) {
        return WorkWithDBForUser.getUserByUsername(account.getUsername()) != null;
    }

    public static boolean emailExist( User account ) {
        return WorkWithDBForUser.getUserByEmail(account.getEmail()) != null;
    }

    public static boolean register(User account, HttpServletRequest req, String repeatPassword) {
        /*перед запросами в бд проверяем пароль, юзернейм и почту во избежание sql иньекций*/
        if (!check(account, req)) { return false; }

        if (usernameExist(account)) {
            req.setAttribute("error", "A user with this username is already registered");
            return false;
        } else if (emailExist(account)) {
            req.setAttribute("error", "A user with this email is already registered");
            return false;
        } else if (!account.getPassword().equals(repeatPassword)) {
            req.setAttribute("error", "Passwords don't match");
            return false;
        } else {
            save(account);
            return true;
        }
    }

    public static void save(User account) {
        WorkWithDBForUser.saveUserToDB(account);
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