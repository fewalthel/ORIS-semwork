package org.example.orissemwork.services;

import org.example.orissemwork.db.UserDAO;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class  SecurityService {
    public static Map<String, Object> getUser(HttpServletRequest req) {
        if (isSigned(req)) {
            Map<String, Object> user = new HashMap<>();
            user.put("username", req.getSession().getAttribute("username"));
            user.put("email", req.getSession().getAttribute("email"));
            return user;
        }
        return null;
    }

    public static boolean isSigned(HttpServletRequest req) {
        return req.getSession().getAttribute("username") != null && req.getSession().getAttribute("email") != null;
    }

    public static boolean signIn(HttpServletRequest req, String email, String password) {
        if (UserDAO.findByEmailAndPassword(email, password)) {
            req.getSession().setAttribute("username", UserDAO.getByEmail(email).getUsername());
            req.getSession().setAttribute("email", email);
            return true;
        } else {
            req.setAttribute("error", "Incorrect email or password");
            return false;
        }
    }

    public static void signOut(HttpServletRequest req) {
        req.getSession().removeAttribute("username");
        req.getSession().removeAttribute("email");
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
