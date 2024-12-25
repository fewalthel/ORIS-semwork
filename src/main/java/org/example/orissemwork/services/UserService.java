package org.example.orissemwork.services;

import org.example.orissemwork.db.UserDAO;
import org.example.orissemwork.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserService {

    public static UserDAO userDAO;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static User getUser(HttpServletRequest req) throws SQLException {
        if (isSigned(req)) {
            String email = (String) req.getSession().getAttribute("email");
            System.out.println("Вызывается метод getUser");
            User user = userDAO.getByEmail(email);
            return user;
        }
        return null;
    }

    public static boolean isSigned(HttpServletRequest req) {
        System.out.println("Вызывается метод isSigned");
        return req.getSession().getAttribute("username") != null && req.getSession().getAttribute("email") != null;
    }

    public boolean signIn(HttpServletRequest req, String email, String password) throws SQLException {
        System.out.println("Вызывается метод signIn");
        if (passwordEncoder.matches(password, userDAO.getByEmail(email).getPassword())) {
            User user = userDAO.getByEmail(email);

            req.getSession().setAttribute("username", user.getUsername());
            req.getSession().setAttribute("email", email);
            req.getSession().setAttribute("id", user.getId());
            req.getSession().setAttribute("role", user.getRole());
            return true;
        } else {
            req.setAttribute("error", "Incorrect email or password");
            return false;
        }
    }

    public void signOut(HttpServletRequest req) {
        req.getSession().removeAttribute("username");
        req.getSession().removeAttribute("email");
        req.getSession().removeAttribute("id");
        req.getSession().removeAttribute("role");
    }

    public void deleteAccount(HttpServletRequest req, HttpServletResponse resp, String current_username, String current_password) throws IOException, SQLException, ServletException {
        User current_user = userDAO.getByEmail((String) req.getSession().getAttribute("email"));

        if (current_user.getUsername().equals(current_username) && current_user.getPassword().equals(current_password)) {
            signOut(req);
            userDAO.deleteFromDB(current_user);
            resp.sendRedirect(req.getServletContext().getContextPath() + "/main");
        } else {
            req.setAttribute("error", "Incorrect username or password");
            req.getRequestDispatcher("/views/profile/settings.jsp").forward(req, resp);
        }
    }

    public void changePasswordOrUsername(HttpServletRequest req, HttpServletResponse resp, String new_username, String old_password, String new_password) throws IOException, SQLException, ServletException {
        if (changesIsSaved(req, new_username, old_password, new_password)) {
            resp.sendRedirect(req.getServletContext().getContextPath() + "/settings");
        } else {
            req.getRequestDispatcher("/views/profile/settings.jsp").forward(req, resp);
        }
    }

    public void adminSettings(HttpServletRequest req, HttpServletResponse resp, String deleted_username, String updated_username) throws SQLException, IOException {
        if (deleted_username != null) {
            User user = userDAO.getByUsername(req.getParameter("deleted_username"));
            userDAO.deleteFromDB(user);
        }
        if (updated_username != null) {
            User user = userDAO.getByUsername(req.getParameter("upgraded_username"));
            userDAO.upgradeRole(user);
        }
        resp.sendRedirect(req.getServletContext().getContextPath() + "/all_users");
    }


    public boolean oldPasswordIsRight(String oldPassword, String username) throws SQLException {
        return passwordEncoder.matches(oldPassword, userDAO.getByUsername(username).getPassword());
    }

    public boolean newPasswordIsValid(String newPassword) {
        return newPassword.matches(RegisterService.PWD_REG);
    }

    public boolean newUsernameIsValid(String newUsername) {
        return newUsername.matches(RegisterService.USERNAME_REG);
    }

    public boolean newUsernameIsUnique(String newUsername) throws SQLException {
        return userDAO.getByUsername(newUsername) == null;
    }

    public void changePassword(HttpServletRequest req, String newPassword) throws SQLException {
        String email = (String) req.getSession().getAttribute("email");
        User user = userDAO.getByEmail(email);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(newPassword);
        userDAO.updatePassword(user, password);
    }

    public void changeUsername(HttpServletRequest req, String newUsername) throws SQLException {
        String email = (String) req.getSession().getAttribute("email");
        User user = userDAO.getByEmail(email);
        userDAO.updateUsername(user, newUsername);
    }

    public boolean changesIsSaved(HttpServletRequest req, String new_username, String old_password, String new_password) throws SQLException {
        if (old_password.isEmpty() && new_password.isEmpty() && new_username.isEmpty()) {
            req.setAttribute("error", "Nobody to update");
            return false;
        }

        if ((old_password.isEmpty() && !new_password.isEmpty()) || (!old_password.isEmpty() && new_password.isEmpty())) {
            req.setAttribute("error", "If you want to change your password, fill all fields");
            return false;
        }

        if (!old_password.isEmpty() && !new_password.isEmpty()) {
            if (!newPasswordIsValid(new_password)) {
                req.setAttribute("error", "Invalid new password");
                return false;
            }
            if (!oldPasswordIsRight(old_password, (String) req.getSession().getAttribute("username"))) {
                req.setAttribute("error", "Incorrect old password");
                return false;
            }
        }

        if (!new_username.isEmpty()) {
            if (!newUsernameIsValid(new_username)) {
                req.setAttribute("error", "Invalid new username");
                return false;
            }
            if (!newUsernameIsUnique(new_username)) {
                req.setAttribute("error", "User with this username already exists");
                return false;
            }
        }

        if (!old_password.isEmpty() && !new_password.isEmpty() && newPasswordIsValid(new_password) && oldPasswordIsRight(old_password, (String) req.getSession().getAttribute("username"))) {
            changePassword(req, new_password);
        }
        if (!new_username.isEmpty() && newUsernameIsValid(new_username) && newUsernameIsUnique(new_username)) {
            changeUsername(req, new_username);
            req.getSession().setAttribute("username", new_username);
        }
        return true;
    }
}