package com.empat.klinik.repository;

import com.empat.klinik.model.dto.projection.Laporan;
import com.empat.klinik.model.entity.Icdx;
import com.empat.klinik.model.entity.Pemesanan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PemesananRepository extends JpaRepository<Pemesanan, Integer> {
    Optional<Pemesanan> findByIdPasienAndKdIcdxAndNik(Integer idPasien, String kdIcdx, Long nik);
    Optional<Pemesanan> findByIdPasien(Integer idPasien);
    Optional<Pemesanan> findByKdIcdx(String kdIcdx);

    //void deleteByIdPasien(String id_pasien);

    //Optional<Pemesanan> findByNoAntrian(Integer noAntrian);

    //Optional<Pemesanan> deleteByIdPasien(String idPasien);
    //Optional<Pemesanan> findByNik(String nik);

    //Pemesanan[] findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM t_pemesanan WHERE id_status_pelayanan = '1'")
    List<Pemesanan> findStatusDone();

    @Query(nativeQuery = true, value = "SELECT no_antrian as noAntrian, nama as namaPasien FROM t_pemesanan WHERE id_status_pelayanan = '1'")
    List<Laporan> laporan();

    @Query(nativeQuery = true, value = "SELECT * FROM t_pemesanan WHERE id_status_pelayanan = '0'")
    List<Pemesanan> getListPemesanan();

    @Query(nativeQuery = true,value = "SELECT COUNT(*) FROM t_pemesanan WHERE tgl_pemesanan = CURRENT_DATE")
    Integer countPemesanan();

    @Query(nativeQuery = true, value = "SELECT DISTINCT * FROM t_pemesanan WHERE tgl_pemesanan = CURRENT_DATE AND id_pasien > 0 ")
    List<Pemesanan> findByTanggalPesan();

    //Optional<Pemesanan> findByIdPemesanan(Integer idPemesanan);

    Optional<Pemesanan> findByNoAntrian(Integer noAntrian);

    Optional<Pemesanan> findByNoAntrianAndTanggalPesan(Integer noAntrian, LocalDate now);

    Optional<Pemesanan> findByIdPasienAndTanggalPesan(Integer idPasien, LocalDate now);


  /*  Optional<Pemesanan> findByIdPasienOrNamaAndTanggalPesan(Integer idPasien, String nama, LocalDate now);
    //Optional<Pemesanan> findByTanggalPesan(LocalDate tanggalPesan);*/
    @Query(nativeQuery = true, value = "SELECT * FROM t_pemesanan WHERE nama LIKE ?%")
    List<Pemesanan> search(String search, LocalDate now);
}
