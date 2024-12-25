package org.example.orissemwork.services;

import org.example.orissemwork.db.UserDAO;
import org.example.orissemwork.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

public class RegisterService {

    private UserDAO userDAO;

    public RegisterService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static final String EMAIL_REG = "[A-z0-9_-]+@[A-z0-9_-]+.[a-z]+";
    public static final String USERNAME_REG = "^[a-zA-Z0-9_.]+$";
    public static final String PWD_REG = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+{};':|,.<>/?]).{8,}$";

    public boolean usernameExist(User account) throws SQLException {
        return userDAO.getByUsername(account.getUsername()) != null;
    }

    public boolean emailExist(User account) throws SQLException {
        return userDAO.getByEmail(account.getEmail()) != null;
    }

    public boolean valuesIsValid(User account, HttpServletRequest req, String repeatPassword) throws SQLException {
        if (!checkData(account, req)) {
            return false;
        }
        if (usernameExist(account)) {
            req.setAttribute("error", "User with this username is already registered");
            return false;
        } else if (emailExist(account)) {
            req.setAttribute("error", "User with this email is already registered");
            return false;
        } else if (!account.getPassword().equals(repeatPassword)) {
            req.setAttribute("error", "Passwords don't match");
            return false;
        } else {
            return true;
        }
    }

    public void save(User account) throws SQLException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(account.getPassword());
        account.setPassword(password);

        userDAO.saveToDB(account);
    }

    public boolean checkData(User account, HttpServletRequest req) {
        if (!account.getEmail().matches(EMAIL_REG)) {
            req.setAttribute("error", "Invalid email");
            return false;
        } else if (!account.getUsername().matches(USERNAME_REG)) {
            req.setAttribute("error", "Invalid username");
            return false;
        } else if (!account.getPassword().matches(PWD_REG)) {
            req.setAttribute("error", "Invalid password");
            return false;
        } else {
            return true;
        }
    }

    public void confirm(HttpServletRequest req, HttpServletResponse resp, String email) throws IOException {
        String code = generateConfirmationCode();
        req.getSession().setAttribute("confirmationCode", code);

        sendEmailConfirmation(email, code);

        resp.sendRedirect(req.getServletContext().getContextPath() + "/confirm");
    }

    private void sendEmailConfirmation(String email, String code) {
        try {
            Properties props = new Properties();
            String emailSender = "notify.00@mail.ru";
            String password = "8NjX2yeU6Ssck8CdrUqq";
            String smtpPort = "587";

            props.put("mail.smtp.host", "smtp.mail.ru");
            props.put("mail.smtp.port", smtpPort);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");


            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailSender, password);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailSender));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Подтверждение почты");
            message.setText("Ваш код подтверждения: " + code);
            Transport.send(message);

        } catch (MessagingException e) {
            System.err.println("Ошибка отправки письма: " + e.getMessage());
        }
    }

    private String generateConfirmationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

}