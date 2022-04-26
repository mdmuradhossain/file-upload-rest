package io.murad.fileupload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service

public class FileService2Impl implements FileService2 {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();
        String fullPath = path + File.separator + name;

        File fileDir = new File(path);

        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        //file copy
        Files.copy(file.getInputStream(), Paths.get(fullPath));
        return name;
    }
}
