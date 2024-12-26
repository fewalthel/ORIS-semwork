package org.example.orissemwork.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import org.example.orissemwork.db.QuestionDAO;
import org.example.orissemwork.db.UserDAO;
import org.example.orissemwork.model.User;
import org.example.orissemwork.services.SecurityService;

@WebFilter("/*")
public class SecurityFilter extends HttpFilter {
    protected final String[] protectedPaths = {"/profile", "/settings", "/my_questions", "/all_questions",
            "/question", "/all_users", "/favorites_answers"};

    protected final String adminPage = "/all_users";

    protected final String onlyParametrizedPath = "/question";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        boolean prot = false;
        for (String path : protectedPaths) {
            if (path.equals(req.getRequestURI().substring(req.getContextPath().length()))) {
                prot = true;
                break;
            }
        }

        if (prot && !SecurityService.isSigned(req)) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User unauthorized");
        } else {
            if (SecurityService.isSigned(req)) {
                req.setAttribute("user", SecurityService.getUser(req));
                if (adminPage.equals(req.getRequestURI().substring(req.getContextPath().length()))) {
                    User user = SecurityService.getUser(req);

                    if (user == null || !"admin".equals(user.getRole())) {
                        res.sendError(HttpServletResponse.SC_FORBIDDEN, "Admins only");
                        return;
                    }
                }

                if (onlyParametrizedPath.equals(req.getRequestURI())) {
                    if (req.getParameterMap().isEmpty()) {
                        res.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
                        return;
                    } else {
                        Integer potencialId = Integer.valueOf(req.getParameterMap().get("id")[0]);

                        if (QuestionDAO.getById(potencialId) == null) {
                            res.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
                            return;
                        }
                    }
                }

            }
            chain.doFilter(req, res);
        }
    }

}