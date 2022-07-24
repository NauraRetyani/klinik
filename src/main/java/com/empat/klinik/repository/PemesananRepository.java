package com.empat.klinik.repository;

import com.empat.klinik.model.entity.Icdx;
import com.empat.klinik.model.entity.Pemesanan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
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

    @Query(nativeQuery = true, value = "SELECT * FROM t_pemesanan WHERE id_status_pelayanan = '1'")
    List<Pemesanan> findStatusDone();

    @Query(nativeQuery = true,value = "SELECT COUNT(*) FROM t_pemesanan WHERE tgl_pemesanan = CURRENT_DATE")
    Integer countPemesanan();
}
