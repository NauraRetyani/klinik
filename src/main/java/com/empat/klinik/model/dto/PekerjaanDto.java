package com.empat.klinik.model.dto;

public class PekerjaanDto {
    private String idPasien;
    private String nama;
    private String namaJob;


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

    public String getNamaJob() {
        return namaJob;
    }

    public void setNamaJob(String namaJob) {
        this.namaJob = namaJob;
    }
}
