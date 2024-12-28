package org.example.orissemwork.services;

import org.example.orissemwork.db.FileInfoDAO;
import org.example.orissemwork.model.FileInfo;
import org.example.orissemwork.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.UUID;

public class FileService {

    private FileInfoDAO fileInfoDAO;

    public FileService(FileInfoDAO fileInfoDAO) {
        this.fileInfoDAO = fileInfoDAO;
    }

    public void saveFileToStorage(InputStream stream, String type, Long size, String name, User user) throws IOException, SQLException {

        FileInfo file = new FileInfo(null, name, UUID.randomUUID().toString(), size, type);
        String path = "/home/fewalthel/IdeaProjects/ORIS-semwork/src/main/webapp/files/" + file.getStorageFileName() + "." + file.getType().split("/")[1];

        Files.copy(stream, Paths.get(path));
        fileInfoDAO.save(file, user);

        System.out.println("загрузка файла");
    }


    public String getPathToAvatarForUser(User user) throws SQLException {
        FileInfo fileInfo = fileInfoDAO.findByIdUser(user.getId());
        if (fileInfo == null) {
            return "files/default_avatar.jpg";
        } else {
            return "files/" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1];
        }
    }
}
