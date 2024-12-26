package org.example.orissemwork.servlets;

import org.example.orissemwork.model.*;
import org.example.orissemwork.services.RegisterService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet ("/confirm")
public class ConfirmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/confirm/confirm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String confirmationCode = (String) req.getSession().getAttribute("confirmationCode");

        if (code.equals(confirmationCode)) {
            String email = (String) req.getSession().getAttribute("email");
            String username = (String) req.getSession().getAttribute("username");
            String password = (String) req.getSession().getAttribute("password");

            User account = new User(null, email, username, password, "default");
            RegisterService.save(account);

            req.getSession().removeAttribute("password");
            req.getSession().removeAttribute("confirmationCode");
            req.getSession().removeAttribute("username");

            resp.sendRedirect(getServletContext().getContextPath() + "/signin");

        } else {
            req.setAttribute("error", "Invalid confirmation code");
            req.getRequestDispatcher("/views/register/register.jsp").forward(req, resp);
        }
    }

}
