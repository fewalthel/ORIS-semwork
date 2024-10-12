package org.example.orissemwork.services;

import org.example.orissemwork.model.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;


public class RegisterService {

    //тут должны быть запросы в бд которые проверяют, существует ли пользователь с таким юзернеймом и почтой
    public static boolean usernameExist( User account ) {
        return true;
    }

    public static boolean emailExist( User account ) {
        return true;
    }

    public static boolean register(User account, HttpServletRequest req, String repeatPassword) {
        return true;
        /*if (usernameExist(account)) {
            req.setAttribute("error", "Пользователь с таким username уже зарегестрирован");
            return false;
        } else if (emailExist(account)) {
            req.setAttribute("error", "Пользователь с таким email уже зарегестрирован");
            return false;
        } else if (!account.getPassword().equals(repeatPassword)) {
            req.setAttribute("error", "Пароли не совпадают");
            return false;
        } else {
            save(account);
            return true;
        }*/
    }

    public static void save(User account) {
        //тут должна быть загрузка данных в бд
    }



}
