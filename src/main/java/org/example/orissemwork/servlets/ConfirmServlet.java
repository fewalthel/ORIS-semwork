package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.model.*;
import org.example.orissemwork.services.RegisterService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet ("/confirm")
public class ConfirmServlet extends HttpServlet {

    private RegisterService registerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        registerService = (RegisterService) getServletContext().getAttribute("registerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/views/confirm/confirm.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String code = req.getParameter("code");
        String confirmationCode = (String) req.getSession().getAttribute("confirmation_code");

        if (code.equals(confirmationCode)) {
            String email = (String) req.getSession().getAttribute("email");
            String username = (String) req.getSession().getAttribute("username");
            String password = (String) req.getSession().getAttribute("password");

            User account = new User(null, email, username, password, "default");
            registerService.save(account);

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
