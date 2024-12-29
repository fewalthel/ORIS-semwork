package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.model.User;
import org.example.orissemwork.services.FileService;
import org.example.orissemwork.services.UserService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/files")
@MultipartConfig
public class UploadAvatarServlet extends HttpServlet {

    private FileService fileService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        fileService = (FileService) getServletContext().getAttribute("fileService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("avatar");

        InputStream stream = part.getInputStream();
        Long size = part.getSize();
        String type = part.getContentType();
        String name  = part.getSubmittedFileName();

        User user = userService.getUser(req);
        fileService.setAvatar(stream, type, size, name, user);

        resp.sendRedirect(req.getContextPath() + "/settings");
    }
}
