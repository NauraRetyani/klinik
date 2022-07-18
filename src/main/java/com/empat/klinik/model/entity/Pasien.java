package com.empat.klinik.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_pasien")
public class Pasien {
    @Id
    @Column(name = "id_pasien", length = 7)
    private String idPasien;
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
    private String idJob;

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
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

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }
}

