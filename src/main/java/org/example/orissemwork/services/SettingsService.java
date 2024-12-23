package org.example.orissemwork.services;

import org.example.orissemwork.db.UserDAO;
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
