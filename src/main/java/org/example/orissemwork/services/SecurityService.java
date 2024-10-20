package org.example.orissemwork.services;

import org.example.orissemwork.db.WorkWithDBForUser;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class SecurityService {
    public static Map<String, Object> getUser(HttpServletRequest req) {
        if(isSigned(req)){
            Map<String, Object> user = new HashMap<>();
            user.put("username", req.getSession().getAttribute("username"));
            return user;
        }
        return null;
    }

    public static boolean isSigned(HttpServletRequest req) { return req.getSession().getAttribute("username") != null; }

    public static boolean signIn(HttpServletRequest req, String email, String password) {
        if (WorkWithDBForUser.findUserByEmailAndPassword(email, password)) {
            req.getSession().setAttribute("username", WorkWithDBForUser.getUserByEmail(email).getUsername());
            return true;
        } else {
            req.setAttribute("error", "Invalid email or password");
            return false;
        }
    }

    public static void signOut(HttpServletRequest req) { req.getSession().removeAttribute("username"); }
}
