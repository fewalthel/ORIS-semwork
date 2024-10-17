package org.example.orissemwork.services;

import org.example.orissemwork.db.WorkWithDB;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class SecurityService {
    public static Map<String, Object> getUser(HttpServletRequest req) {
        if(isSigned(req)){
            Map<String, Object> user = new HashMap<>();
            user.put("email", req.getSession().getAttribute("email"));
            return user;
        }
        return null;
    }

    public static boolean isSigned(HttpServletRequest req) { return req.getSession().getAttribute("email") != null; }

    public static boolean signIn(HttpServletRequest req, String email, String password) {
        if (WorkWithDB.findUserByEmailAndPassword(email, password)) {
            req.getSession().setAttribute("email", email);
            return true;
        } else {
            req.setAttribute("error", "Неверный email или password");
            System.out.println("Неверный email или password");
            return false;
        }
    }

    public static void signOut(HttpServletRequest req) { req.getSession().removeAttribute("email"); }
}
