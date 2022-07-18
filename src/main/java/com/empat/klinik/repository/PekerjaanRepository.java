package com.empat.klinik.repository;

import com.empat.klinik.model.entity.Pasien;
import com.empat.klinik.model.entity.Pekerjaan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PekerjaanRepository extends JpaRepository<Pekerjaan, Integer> {

}
