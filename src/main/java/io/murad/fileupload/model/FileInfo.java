package io.murad.fileupload.model;

import javax.persistence.*;

@Entity
@Table
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fileName;
    private String url;

    public FileInfo(Long id, String fileName, String url) {
        this.id = id;
        this.fileName = fileName;
        this.url = url;
    }

    public FileInfo(String fileName, String url) {
        this.fileName = fileName;
        this.url = url;
    }

    public FileInfo() {

    }
}
