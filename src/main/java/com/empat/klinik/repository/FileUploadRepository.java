package com.empat.klinik.repository;

import com.empat.klinik.model.entity.UploadeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<UploadeFile, String> {

}
