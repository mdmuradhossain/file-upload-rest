package io.murad.fileupload.controller;

import io.murad.fileupload.response.FileResponse;
import io.murad.fileupload.service.FileService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController2 {

    @Autowired
    private FileService2 fileService2;

    @Value("${project.image}")
    String path;

    @PostMapping("/uploadImage")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("image") MultipartFile image) {
        String fileName = null;

        try {
            fileName = fileService2.uploadImage(path, image);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null, "Uploaded Successfully..."),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(fileName, "Uploaded Successfully..."), HttpStatus.OK);
    }
}
