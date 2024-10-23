package org.example.orissemwork.services;

import org.example.orissemwork.db.WorkWithDBForUser;
import org.example.orissemwork.model.User;

import javax.servlet.http.HttpServletRequest;

public class SettingsService {
    public static boolean oldPasswordIsRight(String oldPassword, String username) {
        return WorkWithDBForUser.getUserByUsername(username).getPassword().equals(oldPassword);
    }

    public static boolean newPasswordIsValid(String newPassword) {
        return newPassword.matches(RegisterService.PWD_REG);
    }

    public static boolean newUsernameIsValid(String newUsername) {
        return newUsername.matches(RegisterService.USERNAME_REG);
    }

    public static boolean newUsernameIsUnique(String newUsername) {
        return WorkWithDBForUser.getUserByUsername(newUsername) == null;
    }

    public static void changePassword(HttpServletRequest req, String newPassword) {
        String email = (String) req.getSession().getAttribute("email");
        User user = WorkWithDBForUser.getUserByEmail(email);
        WorkWithDBForUser.updatePassword(user, newPassword);
    }

    public static void changeUsername(HttpServletRequest req, String newUsername) {
        String email = (String) req.getSession().getAttribute("email");
        User user = WorkWithDBForUser.getUserByEmail(email);
        WorkWithDBForUser.updateUsername(user, newUsername);
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
            } else if (!oldPasswordIsRight(old_password, (String) req.getSession().getAttribute("username"))) {
                req.setAttribute("error", "Incorrect old password");
                return false;
            } else {
                changePassword(req, new_password);
            }
        }

        if (!new_username.isEmpty()) {
            if (!newUsernameIsValid(new_username)) {
                req.setAttribute("error", "Invalid new username");
                return false;
            } else if (!newUsernameIsUnique(new_username)) {
                req.setAttribute("error", "User with this username already exists");
                return false;
            } else {
                changeUsername(req, new_username);
                req.getSession().setAttribute("username", new_username);
            }
        }

        return true;
    }
}
