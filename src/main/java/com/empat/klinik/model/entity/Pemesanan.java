package com.empat.klinik.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_pemesanan")
public class Pemesanan {
    @Column
    private String noAntrian;
    @Id
    @Column
    private String idPasien;
    @Column
    private String namaPasien; //Nanti nama pasien ini menurut saya di join dari tabel pasien aja
    @Column
    private String idPenyakit;

    @OneToOne
    @JoinColumn (referencedColumnName = "nama_icdx")
    private Icdx icdx;

    public Icdx getIcdx() {
        return icdx;
    }

    public void setIcdx(Icdx icdx) {
        this.icdx = icdx;
    }

    @Column
    private String idKaryawan;
    @Column
    private String statusPelayanan;



    public String getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(String noAntrian) {
        this.noAntrian = noAntrian;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getIdPenyakit() {
        return idPenyakit;
    }

    public void setIdPenyakit(String idPenyakit) {
        this.idPenyakit = idPenyakit;
    }

    public String getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(String idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getStatusPelayanan() {
        return statusPelayanan;
    }

    public void setStatusPelayanan(String statusPelayanan) {
        this.statusPelayanan = statusPelayanan;
    }
}
