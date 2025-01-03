package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.dao.CategoryDAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.orissemwork.model.Category;


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
        getServletContext().getRequestDispatcher("/views/profile/all_categories.jsp").forward(req, resp);
    }


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // приняли JSON на вход, с помоьщью ObjectMapper-а превратили в User-объект
        Category category = objectMapper.readValue(req.getReader(), Category.class);
        // сохранили нового пользователя в бд
        categoryDAO.saveToDB(category);

        // получили всех пользователей из бд
        List<Category> categories = categoryDAO.getAll();
        // сформировали JSON-строку-ответ
        String categoriesAsJson = objectMapper.writeValueAsString(categories);
        resp.setContentType("application/json");
        // отправили ответ
        resp.getWriter().println(categoriesAsJson);
    }

}
