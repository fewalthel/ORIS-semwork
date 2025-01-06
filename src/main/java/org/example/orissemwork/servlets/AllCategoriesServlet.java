package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.dao.CategoryDAO;
import org.example.orissemwork.model.Category;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/all_categories")
public class AllCategoriesServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    private CategoryDAO categoryDAO;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        categoryDAO = (CategoryDAO) getServletContext().getAttribute("categoryDAO");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("all_categories", categoryDAO.getAll());
        getServletContext().getRequestDispatcher("/views/admin/all_categories.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = objectMapper.readValue(req.getReader(), Category.class);
        categoryDAO.saveToDB(category);
        List<Category> categories = categoryDAO.getAll();
        String categoriesAsJson = objectMapper.writeValueAsString(categories);
        resp.setContentType("application/json");
        resp.getWriter().println(categoriesAsJson);
    }
}