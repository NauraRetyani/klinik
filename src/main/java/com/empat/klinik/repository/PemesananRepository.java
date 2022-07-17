package com.empat.klinik.repository;

import com.empat.klinik.model.entity.Icdx;
import com.empat.klinik.model.entity.Pemesanan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PemesananRepository extends JpaRepository<Pemesanan, String> {
    Optional<Pemesanan> findByIdPasien(String idPasien);

    //Pemesanan[] findAll();
}
