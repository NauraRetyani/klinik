package com.empat.klinik.repository;

import com.empat.klinik.model.entity.Icdx;
import com.empat.klinik.model.entity.Pemesanan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PemesananRepository extends JpaRepository<Pemesanan, Integer> {
    Optional<Pemesanan> findByIdPasienAndKdIcdxAndNik(Integer idPasien, String kdIcdx, Long nik);
    Optional<Pemesanan> findByIdPasien(Integer idPasien);
    Optional<Pemesanan> findByKdIcdx(String kdIcdx);

    //void deleteByIdPasien(String id_pasien);

    Optional<Pemesanan> findByNoAntrian(Integer noAntrian);

    //Optional<Pemesanan> deleteByIdPasien(String idPasien);
    //Optional<Pemesanan> findByNik(String nik);

    //Pemesanan[] findAll();
}
