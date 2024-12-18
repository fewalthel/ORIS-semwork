package org.example.orissemwork.services;

import javax.mail.*;
import javax.mail.internet.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class ConfirmService {

    public void confirm(HttpServletRequest req, HttpServletResponse resp, String email) throws IOException {

        String code = generateConfirmationCode();
        HttpSession session = req.getSession();
        session.setAttribute("confirmationCode", code);

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
