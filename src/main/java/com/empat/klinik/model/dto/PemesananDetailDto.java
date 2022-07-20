package com.empat.klinik.model.dto;

public class PemesananDetailDto {
    private Integer noAntrian;
    private String nama;
    private String namaIcdx;
    //private String namaKaryawan;
    private String namaKaryawan;
    private String statusPelayanan;

    public Integer getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(Integer noAntrian) {
        this.noAntrian = noAntrian;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNamaIcdx() {
        return namaIcdx;
    }

    public void setNamaIcdx(String namaIcdx) {
        this.namaIcdx = namaIcdx;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public String getStatusPelayanan() {
        return statusPelayanan;
    }

    public void setStatusPelayanan(String statusPelayanan) {
        this.statusPelayanan = statusPelayanan;
    }
}
