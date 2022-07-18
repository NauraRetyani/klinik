package com.empat.klinik.model.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_pasien")
public class Pasien {
    @Id
    @Column(name = "id_pasien")
    private Long idPasien;
    @Column
    private String nama;
    @Column
    private String gender;
    @Column
    private Date bday;
    @Column(name = "gol_darah")
    private String golDar;
    @Column
    private String alamat;
    @Column(name = "id_pekerjaan")
    private Integer idJob;

    @OneToOne
    @JoinColumn(name = "id_pekerjaan", insertable = false, updatable = false)
    private Pekerjaan pekerjaan;

    public Pasien() {
    }

    public Long getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(Long idPasien) {
        this.idPasien = idPasien;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    public String getGolDar() {
        return golDar;
    }

    public void setGolDar(String golDar) {
        this.golDar = golDar;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Integer getIdJob() {
        return idJob;
    }

    public void setIdJob(Integer idJob) {
        this.idJob = idJob;
    }

    public Pekerjaan getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(Pekerjaan pekerjaan) {
        this.pekerjaan = pekerjaan;
    }
}


