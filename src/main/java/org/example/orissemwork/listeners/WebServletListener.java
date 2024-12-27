package org.example.orissemwork.listeners;

import org.example.orissemwork.db.*;
import org.example.orissemwork.services.*;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;


@WebListener
public class WebServletListener implements ServletContextListener {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/oris";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        RatingDAO ratingDAO = new RatingDAO(dataSource);
        RatingService ratingService = new RatingService(ratingDAO);

        UserDAO userDAO = new UserDAO(dataSource);
        UserService userService = new UserService(userDAO);

        QuestionDAO questionDAO = new QuestionDAO(dataSource);
        QuestionService questionService = new QuestionService(questionDAO);

        AnswerDAO answerDAO = new AnswerDAO(dataSource);
        AnswerService answerService = new AnswerService(answerDAO);
        RegisterService registerService = new RegisterService(userDAO);

        servletContext.setAttribute("ratingService", ratingService);
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("questionService", questionService);
        servletContext.setAttribute("answerService", answerService);
        servletContext.setAttribute("registerService", registerService);
        servletContext.setAttribute("ratingDAO", ratingDAO);
        servletContext.setAttribute("userDAO", userDAO);
        servletContext.setAttribute("questionDAO", questionDAO);
        servletContext.setAttribute("answerDAO", answerDAO);
    }

}
