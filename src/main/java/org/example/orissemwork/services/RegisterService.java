package org.example.orissemwork.services;

import org.example.orissemwork.db.WorkWithDB;
import org.example.orissemwork.model.User;

import javax.servlet.http.HttpServletRequest;

public class RegisterService {
    private static final String EMAIL_REG = "[A-z0-9_-]+@[A-z0-9_-]+.[a-z]+";
    private static final String USERNAME_REG = "^[a-zA-Z0-9]+$";
    private static final String PWD_REG = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+-{};':|,.<>/?]).{8,}$";

    public static boolean usernameExist( User account ) {
        return WorkWithDB.getUserByUsername(account.getUsername()) != null;
    }

    public static boolean emailExist( User account ) {
        return WorkWithDB.getUserByEmail(account.getEmail()) != null;
    }

    public static boolean register(User account, HttpServletRequest req, String repeatPassword) {
        /*перед запросами в бд проверяем пароль, юзернейм и почту
        во избежание sql иньекций*/
        if (check(account, req)) {
            if (usernameExist(account)) {
                req.setAttribute("error", "Пользователь с таким username уже зарегестрирован");
                System.out.println("Пользователь с таким username уже зарегестрирован");
                return false;
            } else if (emailExist(account)) {
                req.setAttribute("error", "Пользователь с таким email уже зарегестрирован");
                System.out.println("Пользователь с таким email уже зарегестрирован");
                return false;
            } else if (!account.getPassword().equals(repeatPassword)) {
                req.setAttribute("error", "Пароли не совпадают");
                return false;
            } else {
                save(account);
                return true;
            }
        } else {
            return false;
        }
    }

    public static void save(User account) {
        WorkWithDB.saveUserToDB(account);
    }

    public static boolean check(User account, HttpServletRequest req) {
        if (!account.getEmail().matches(EMAIL_REG)) {
            req.setAttribute("error", "Неверный email");
            return false;
        } else if (!account.getUsername().matches(USERNAME_REG)) {
            req.setAttribute("error", "Неверный username");
            return false;
        } else if (!account.getPassword().matches(PWD_REG)) {
            req.setAttribute("error", "Неверный пароль");
            return false;
        } else {
            return true;
        }
    }
}