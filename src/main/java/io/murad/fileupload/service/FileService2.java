package io.murad.fileupload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService2 {

    public String uploadImage(String path, MultipartFile file) throws IOException;
}
