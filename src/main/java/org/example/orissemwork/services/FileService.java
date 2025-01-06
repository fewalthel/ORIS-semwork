package org.example.orissemwork.services;

import org.example.orissemwork.dao.FileInfoDAO;
import org.example.orissemwork.model.FileInfo;
import org.example.orissemwork.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.UUID;
import java.io.InputStream;

public class FileService {

    private FileInfoDAO fileInfoDAO;
    private UserService userService;

    public FileService(FileInfoDAO fileInfoDAO, UserService userService) {
        this.fileInfoDAO = fileInfoDAO;
        this.userService = userService;
    }

    private static String pathToFolder = "/home/fewalthel/files/";

    public void saveFileToStorage(InputStream stream, String type, Long size, String name, User user) throws IOException, SQLException {
        FileInfo file = new FileInfo(null, name, UUID.randomUUID().toString(), size, type, null);
        String path = pathToFolder + file.getStorageFileName() + "." + file.getType().split("/")[1];
        Files.copy(stream, Paths.get(path));
        fileInfoDAO.save(file, user);
    }

    public void setAvatar(InputStream stream, String type, Long size, String name, User user) throws SQLException, IOException {
        FileInfo fileInfo = fileInfoDAO.getByIdUser(user.getId());

        if (fileInfo != null) {
            if (!fileInfo.getStorageFileName().equals("6be2c66b-a82e-48bc-bb79-b0dabec64928")) {
                fileInfoDAO.deleteFromDB(user);
                //удаление старой аватарки с сервера
                String path = pathToFolder + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1];
                Files.delete(Paths.get(path));
            }
        }
        saveFileToStorage(stream, type, size, name, user);
    }

    public void setDefaultAvatar(User account) throws SQLException, IOException {
        Long size = 11023L;
        FileInfo fileInfo = new FileInfo(null, "default_avatar.jpeg", "6be2c66b-a82e-48bc-bb79-b0dabec64928", size, "image/jpeg", account.getId());
        fileInfoDAO.save(fileInfo, account);
    }

    public boolean fileIsUploaded(Part part, HttpServletRequest req) throws IOException, SQLException {
        InputStream stream = part.getInputStream();
        Long size = part.getSize();
        String type = part.getContentType();
        String name = part.getSubmittedFileName();

        if (!type.startsWith("image/")) {
            req.setAttribute("error", "Only images can be uploaded");
            return false;
        } else {
            User user = userService.getUser(req);
            setAvatar(stream, type, size, name, user);
            return true;
        }
    }

    public void writeFileFromStorage(Integer id_user, OutputStream outputStream) throws SQLException, IOException {
        FileInfo fileInfo = fileInfoDAO.getByIdUser(id_user);
        File file = new File(pathToFolder + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);
        Files.copy(file.toPath(), outputStream);
    }
}