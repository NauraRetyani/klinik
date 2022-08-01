package com.empat.klinik.controller;

import com.empat.klinik.model.dto.FileUploadResponse;
import com.empat.klinik.model.entity.UploadeFile;
import com.empat.klinik.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

/*Ini trial untuk nambahin data foto pasien, cuma masih dalam satu tabel terpisah, saya masih
belum paham cara agar langsung dalam satu tabel. Seharusnya sih di Join bisa,
 cuma belum saya coba lagi*/

@RestController
@RequestMapping("pasien/api/v1")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    //Upload file to local
    @PostMapping("upload/local")
    public void uploadLocal(@RequestParam("file")MultipartFile multipartFile){
        //
        fileUploadService.uplodToLocal(multipartFile);
    }

    //UploadFileto Db
    @PostMapping("upload/db")
    public FileUploadResponse uploadDB(@RequestParam("file")MultipartFile multipartFile){
        //
        FileUploadResponse response = new FileUploadResponse();
        UploadeFile uploadeFile=fileUploadService.uploadToDB(multipartFile);
        if (uploadeFile!=null){
           String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                   .path("pasien/api/v1/download/")
                   .path(uploadeFile.getFileId())
                   .toUriString();
           response.setDownloadUrl(downloadUrl);
           response.setFileId(uploadeFile.getFileId());
           response.setFileType(uploadeFile.getFileTypes());
           response.setFileName(uploadeFile.getFileName());
           response.setUploadStatus(true);
           response.setMessage("Upload Succesfully");
           return response;
        }
        response.setMessage("Oops 1 something went wrong. Please reupload");
        return  response;
    }


    //DownloadFile which our upload to DB
    @GetMapping("/download/{fileId}")
    /*public Optional<UploadeFile> downloadFile(@PathVariable String fileId){
        return fileUploadService.downloadFile(fileId);

    }*/
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId){
        Optional<UploadeFile> uploadFileToRet = fileUploadService.downloadFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(uploadFileToRet.get().getFileTypes()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= "+uploadFileToRet.get().getFileName())
                .body(new ByteArrayResource(uploadFileToRet.get().getFileData()));
    }
}
