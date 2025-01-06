package org.example.orissemwork.servlets;

import lombok.SneakyThrows;
import org.example.orissemwork.dao.FileInfoDAO;
import org.example.orissemwork.model.FileInfo;
import org.example.orissemwork.services.FileService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

// /avatar?id_user=
@WebServlet("/avatar")
public class FilesServlet extends HttpServlet {

    private FileService fileService;
    private FileInfoDAO fileInfoDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.fileService = (FileService) config.getServletContext().getAttribute("fileService");
        this.fileInfoDAO = (FileInfoDAO) config.getServletContext().getAttribute("fileInfoDAO");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        Integer id_user = Integer.parseInt(req.getParameter("id_user"));
        FileInfo fileInfo = fileInfoDAO.getByIdUser(id_user);
        response.setContentType(fileInfo.getType());
        response.setContentLength(fileInfo.getSize().intValue());
        response.setHeader("Content-Disposition", "filename=\"" + fileInfo.getOriginalFileName() + "\"");
        fileService.writeFileFromStorage(id_user, response.getOutputStream());
        response.flushBuffer();
    }
}