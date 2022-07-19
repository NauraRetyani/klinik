package com.empat.klinik.repository;

import com.empat.klinik.model.entity.Icdx;
import com.empat.klinik.model.entity.Pemesanan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PemesananRepository extends JpaRepository<Pemesanan, String> {
    Optional<Pemesanan> findByIdPasienAndKdIcdx(String idPasien, String kdIcdx);
    Optional<Pemesanan> findByIdPasien(String idPasien);
    Optional<Pemesanan> findByKdIcdx(String kdIcdx);

    //void deleteByIdPasien(String id_pasien);

    Optional<Pemesanan> findByNoAntrian(String noAntrian);

    //Optional<Pemesanan> deleteByIdPasien(String idPasien);
    //Optional<Pemesanan> findByNik(String nik);

    //Pemesanan[] findAll();
}
