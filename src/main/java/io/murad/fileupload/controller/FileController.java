package io.murad.fileupload.controller;

import io.murad.fileupload.model.FileInfo;
import io.murad.fileupload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") MultipartFile[] files) {

        String message = "";

        try {
            List<String> fileDetails = new ArrayList<>();

            Arrays.stream(files).forEach(file -> {

                fileService.save(file);
                fileDetails.add(file.getOriginalFilename());
            });

            message = "File Uploaded..." + fileDetails;
            return ResponseEntity.status(HttpStatus.OK).body(message);

        } catch (Exception e) {
            message = "File upload failed...";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);

        }
    }

    @GetMapping()
    public ResponseEntity<List<FileInfo>> getAllFiles() {

        List<FileInfo> fileInfoList = fileService.loadAll().map(path -> {

            String fileName = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
                    path.getFileName().toString()).build().toString();
            return new FileInfo(fileName, url);

        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfoList);
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" + file.getFilename() + "\"").body(file);

    }
}
