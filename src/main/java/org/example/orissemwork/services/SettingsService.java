package org.example.orissemwork.services;

import org.example.orissemwork.db.AnswerDAO;
import org.example.orissemwork.db.UserDAO;
import org.example.orissemwork.model.Answer;
import org.example.orissemwork.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;

public class SettingsService {
    public static boolean oldPasswordIsRight(String oldPassword, String username) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(oldPassword, UserDAO.getByUsername(username).getPassword());

    }

    public static boolean newPasswordIsValid(String newPassword) {
        return newPassword.matches(RegisterService.PWD_REG);
    }

    public static boolean newUsernameIsValid(String newUsername) {
        return newUsername.matches(RegisterService.USERNAME_REG);
    }

    public static boolean newUsernameIsUnique(String newUsername) {
        return UserDAO.getByUsername(newUsername) == null;
    }

    public static void changePassword(HttpServletRequest req, String newPassword) {
        String email = (String) req.getSession().getAttribute("email");
        User user = UserDAO.getByEmail(email);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(newPassword);
        UserDAO.updatePassword(user, password);
    }

    public static void changeUsername(HttpServletRequest req, String newUsername) {
        String email = (String) req.getSession().getAttribute("email");
        User user = UserDAO.getByEmail(email);
        UserDAO.updateUsername(user, newUsername);
    }

    public static void deleteAccount(User user) {
        UserDAO.deleteFromDB(user);
    }

    public static void adminSettings(HttpServletRequest req) {
        if (req.getParameter("deleted_username") != null) {
            User user = UserDAO.getByUsername(req.getParameter("deleted_username") );
            SettingsService.deleteAccount(user);
        }
        if (req.getParameter("upgraded_username") != null) {
            User user = UserDAO.getByUsername(req.getParameter("upgraded_username"));
            UserDAO.upgradeRole(user);
        }
    }

    public static boolean accountDeleted(HttpServletRequest req, User current_user, String current_username, String current_password) {
        if (current_user.getUsername().equals(current_username) && current_user.getPassword().equals(current_password)) {
            SecurityService.signOut(req);
            SettingsService.deleteAccount(current_user);
            return true;
        } else {
            req.setAttribute("error", "Incorrect username or password");
            return false;
        }
    }

    public static void favotiresSettings(HttpServletRequest req) {
        String email_of_user = req.getSession().getAttribute("email").toString();
        Integer id_of_answer = Integer.parseInt(req.getParameter("id_of_answer"));

        User user = UserDAO.getByEmail(email_of_user);
        Answer ans = AnswerDAO.getById(id_of_answer);

        if (AnswerDAO.ansInFavForUser(ans, user)) {
            AnswerDAO.removeFromFavorites(ans, user);
        } else {
            AnswerDAO.saveToFavorites(ans, user);
        }
    }

    public static boolean changesIsSaved(HttpServletRequest req, String new_username, String old_password, String new_password){
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
