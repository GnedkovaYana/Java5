package org.example.service;

import java.io.File;
import java.io.IOException;

public class FileService {
    public File[] getDirectories(String path) {
        if (path == null) {
            path = "D:\\";
        }
        File directory = new File(path);
        return directory.listFiles(File::isDirectory);
    }

    public File[] getFiles(String path) {
        if (path == null) {
            path = "D:\\";
        }
        File file = new File(path);
        return file.listFiles(File::isFile);
    }

    public void createDirectory(String path) throws IOException {
        File directory = new File(path);
        if (!directory.exists()) {
            boolean result = directory.mkdir();
            if (!result) {
                throw new IOException("Не удалось создать директорию по этому пути");
            }
        }
    }
}