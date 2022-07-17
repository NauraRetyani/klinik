package com.empat.klinik.model.dto;

import com.empat.klinik.model.entity.Icdx;

public class PemesananDto {
    private String noAntrian;
    private String namaPasien;

    /*Kenapa di sini munculnya namaPenyakit? gua berniat
    menjoinkan id penyakit di entity pemesanan dengan id
    penyakit di entity Icdx. Karena kalo yang ditampilin adalah
    id penyakit jadi ga enak aja sih bingug juga tar adminnya
    wkwkwk. Tapi yang diinput tetep sesuai entitas.
    Kira-kira bisa ga ya?*/

    private Icdx icdx;
    private String statusPelayanan;

    public String getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(String noAntrian) {
        this.noAntrian = noAntrian;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public Icdx getIcdx() {
        return icdx;
    }

    public void setIcdx(Icdx icdx) {
        this.icdx = icdx;
    }

    public String getStatusPelayanan() {
        return statusPelayanan;
    }

    public void setStatusPelayanan(String statusPelayanan) {
        this.statusPelayanan = statusPelayanan;
    }
}
