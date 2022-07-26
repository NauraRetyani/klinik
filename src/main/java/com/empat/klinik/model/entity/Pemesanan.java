package com.empat.klinik.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "t_pemesanan")
public class Pemesanan {

    @Column(name = "no_antrian")
    private Integer noAntrian;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pemesanan_generator")
    @SequenceGenerator(name = "pemesanan_generator", sequenceName = "seq_pemesanan", initialValue = 1, allocationSize = 1)
    @Column(name = "id_pemesanan")
    private Integer idPemesanan;

    @Column(name = "id_pasien", length = 6)
    private Integer idPasien;
    @OneToOne
    @JoinColumn(name = "id_pasien", insertable = false, updatable = false)
    private Pasien pasien;
    @Column(name = "id_penyakit")
    private String kdIcdx;
    @OneToOne
    @JoinColumn(name = "id_penyakit", insertable = false, updatable = false)
    private Icdx icdx;

    @Column(name = "nik_karyawan")
    private Long nik;
    @OneToOne
    @JoinColumn(name = "nik_karyawan", insertable = false, updatable = false)
    private Karyawan karyawan;

    @Column(name = "id_status_pelayanan", length = 1)
    private String statusPelayanan;

    @Column(name = "tgl_pemesanan")
    private LocalDate tanggalPesan;

    @Column(name = "id_pekerjaan")
    private Integer idPekerjaan;

    @OneToOne
    @JoinColumn(name = "id_pekerjaan", insertable = false, updatable = false)
    private Pekerjaan pekerjaan;

    @Column(name = "nama")
    private String nama;

    public Integer getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(Integer noAntrian) {
        this.noAntrian = noAntrian;
    }

    public Integer getIdPemesanan() {
        return idPemesanan;
    }

    public void setIdPemesanan(Integer idPemesanan) {
        this.idPemesanan = idPemesanan;
    }

    public Integer getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(Integer idPasien) {
        this.idPasien = idPasien;
    }

    public String getKdIcdx() {
        return kdIcdx;
    }

    public void setKdIcdx(String kdIcdx) {
        this.kdIcdx = kdIcdx;
    }

    public String getStatusPelayanan() {
        return statusPelayanan;
    }

    public void setStatusPelayanan(String statusPelayanan) {
        this.statusPelayanan = statusPelayanan;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    public Icdx getIcdx() {
        return icdx;
    }

    public void setIcdx(Icdx icdx) {
        this.icdx = icdx;
    }

    public Long getNik() {
        return nik;
    }

    public void setNik(Long nik) {
        this.nik = nik;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public LocalDate getTanggalPesan() {
        return tanggalPesan;
    }

    public void setTanggalPesan(LocalDate tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }

    public Pekerjaan getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(Pekerjaan pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getIdPekerjaan() {
        return idPekerjaan;
    }

    public void setIdPekerjaan(Integer idPekerjaan) {
        this.idPekerjaan = idPekerjaan;
    }
}
