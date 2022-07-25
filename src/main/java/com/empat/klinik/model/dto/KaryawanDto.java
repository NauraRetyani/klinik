package com.empat.klinik.model.dto;

public class KaryawanDto {

    private Long nik;
    private String namaKaryawan;
    private String username;
    private String password;
    private String confirmPassword;
    private Long telp;
    private String alamat;

    public Long getNik() {
        return nik;
    }

    public void setNik(Long nik) {
        this.nik = nik;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getTelp() {
        return telp;
    }

    public void setTelp(Long telp) {
        this.telp = telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
