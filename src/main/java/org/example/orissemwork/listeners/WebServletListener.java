package org.example.orissemwork.listeners;


import org.example.orissemwork.services.RatingService;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.example.orissemwork.db.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
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

        servletContext.setAttribute("ratingService", ratingService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
