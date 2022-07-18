package com.empat.klinik.repository;

import com.empat.klinik.model.entity.Pasien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PasienRepository extends JpaRepository<Pasien, Long> {
    Optional<Pasien> findByIdPasien(String idPasien);

    List<Pasien> findAllByNama(String idPasien);

}
