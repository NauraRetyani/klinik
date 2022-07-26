package com.empat.klinik.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class PemesananDto {
    private Integer noAntrian;
    private Integer idPemesanan;
    private Integer idPasien;
    private String kdIcdx;
    private Long nik;
    private String statusPelayanan;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate tanggalPesan;

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

    public Long getNik() {
        return nik;
    }

    public void setNik(Long nik) {
        this.nik = nik;
    }

    public String getStatusPelayanan() {
        return statusPelayanan;
    }

    public void setStatusPelayanan(String statusPelayanan) {
        this.statusPelayanan = statusPelayanan;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate getTanggalPesan() {
        return tanggalPesan;
    }

    public void setTanggalPesan(LocalDate tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }

}
