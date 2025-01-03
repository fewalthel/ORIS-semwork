package org.example.orissemwork.filters;

import lombok.SneakyThrows;
import org.example.orissemwork.dao.QuestionDAO;
import org.example.orissemwork.services.UserService;
import org.example.orissemwork.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter("/*")
public class SecurityFilter extends HttpFilter {
    protected final String[] protectedPaths = {"/profile", "/settings", "/my_questions", "/all_questions",
            "/question", "/all_users", "/favorites_answers"};

    private UserService userService;
    private QuestionDAO questionDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = (UserService) filterConfig.getServletContext().getAttribute("userService");
        questionDAO = (QuestionDAO) filterConfig.getServletContext().getAttribute("questionDAO");
    }

    @SneakyThrows
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        boolean prot = false;
        for (String path : protectedPaths) {
            if (path.equals(req.getRequestURI().substring(req.getContextPath().length()))) {
                prot = true;
                break;
            }
        }

        if (prot && !UserService.isSigned(req)) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User unauthorized");
        } else {
            if (UserService.isSigned(req)) {

                User user = userService.getUser(req);
                req.setAttribute("user", user);

                if ("/all_users".equals(req.getRequestURI().substring(req.getContextPath().length()))) {
                                        if (user == null || !"admin".equals(user.getRole())) {
                        res.sendError(HttpServletResponse.SC_FORBIDDEN, "Admins only");
                        return;
                    }
                }

                if ("/question".equals(req.getRequestURI())) {
                    if (req.getParameterMap().isEmpty()) {
                        res.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
                        return;
                    } else {
                        Integer potencialId = Integer.valueOf(req.getParameterMap().get("id")[0]);

                        if (questionDAO.getById(potencialId) == null) {
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