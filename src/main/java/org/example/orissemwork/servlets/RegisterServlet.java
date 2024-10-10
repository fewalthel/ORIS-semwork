package org.example.orissemwork.servlets;

import org.example.orissemwork.services.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //тут должна быть логика авторизации

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");

       /* if (RegisterService.usernameExist()) {
            req.setAttribute("error", "Пользователь с таким username уже зарегестрирован");
        } else if (RegisterService.emailExist()) {
            req.setAttribute("error", "Пользователь с таким email уже зарегестрирован");
        }*/



/*        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if(email != null && password != null){
            if(SecurityService.signIn(req, email, password)){
                resp.sendRedirect(getServletContext().getContextPath() + "/profile");
                return;
            }
        }
        req.setAttribute("email", req.getParameter("email"));
        getServletContext().getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req, resp);*/
    }
}