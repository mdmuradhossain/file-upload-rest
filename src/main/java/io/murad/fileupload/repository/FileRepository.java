package io.murad.fileupload.repository;

import io.murad.fileupload.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileInfo,Long> {
}
