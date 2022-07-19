package com.empat.klinik.model.dto;

public class PemesananDetailDto {
    private String noAntrian;
    private String nama;
    private String namaIcdx;
    //private String namaKaryawan;
    private String statusPelayanan;

    public String getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(String noAntrian) {
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

    public String getStatusPelayanan() {
        return statusPelayanan;
    }

    public void setStatusPelayanan(String statusPelayanan) {
        this.statusPelayanan = statusPelayanan;
    }
}
