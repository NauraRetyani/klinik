package com.empat.klinik.model.dto;

import java.util.Date;

public class PasienDto {
    private String idPasien;
    private String nama;
    private String gender;
    private Date bday;
    private String golDar;
    private String alamat;
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
