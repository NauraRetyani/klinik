package com.empat.klinik.model.dto;

public class PekerjaanDto {
    private Integer idPasien;
    private String nama;
    private String namaJob;


    public Integer getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(Integer idPasien) {
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
