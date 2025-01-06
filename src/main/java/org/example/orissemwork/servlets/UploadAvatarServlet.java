package org.example.orissemwork.servlets;

import org.example.orissemwork.services.FileService;

import lombok.SneakyThrows;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/files")
@MultipartConfig
public class UploadAvatarServlet extends HttpServlet {

    private FileService fileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        fileService = (FileService) getServletContext().getAttribute("fileService");
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("avatar");

        if (fileService.fileIsUploaded(part, req)) {
            resp.sendRedirect(req.getContextPath() + "/settings");
        } else {
            getServletContext().getRequestDispatcher("/views/profile/settings.jsp").forward(req, resp);
        }
    }
}
