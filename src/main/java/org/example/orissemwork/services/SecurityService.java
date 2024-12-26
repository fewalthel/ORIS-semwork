package org.example.orissemwork.services;

import org.example.orissemwork.db.UserDAO;
import org.example.orissemwork.model.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class  SecurityService {
    public static User getUser(HttpServletRequest req) {
        if (isSigned(req)) {
            String username = (String) req.getSession().getAttribute("username");
            String email = (String) req.getSession().getAttribute("email");
            String role = (String) req.getSession().getAttribute("role");
            Integer id = (Integer) req.getSession().getAttribute("id");
            return new User (id, email, username, null, role);
        }
        return null;
    }

    public static boolean isSigned(HttpServletRequest req) {
        return req.getSession().getAttribute("username") != null && req.getSession().getAttribute("email") != null;
    }

    public static boolean signIn(HttpServletRequest req, String email, String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(password, UserDAO.getByEmail(email).getPassword())) {
            User user = UserDAO.getByEmail(email);
            req.getSession().setAttribute("username", user.getUsername());
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("role", user.getRole());
            req.getSession().setAttribute("id", user.getId());
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

}