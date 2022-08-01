package com.empat.klinik.service;

import com.empat.klinik.model.entity.UploadeFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface FileUploadService {
    public void uplodToLocal(MultipartFile file);
    public UploadeFile uploadToDB(MultipartFile file);
    public Optional<UploadeFile> downloadFile(String fileId);
}
