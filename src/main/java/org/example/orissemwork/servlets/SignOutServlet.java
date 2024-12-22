package org.example.orissemwork.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.example.orissemwork.services.SecurityService;

@WebServlet("/signout")
public class SignOutServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SecurityService.signOut(req);
        resp.sendRedirect(getServletContext().getContextPath() + "/");
    }
}