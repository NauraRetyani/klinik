package com.empat.klinik.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_karyawan")
public class Karyawan {
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Id
    @Column(name = "NIK", length = 16)
    private String nik;
    @Column(name = "Nama")
    private String nama;
    @Column(name = "Alamat")
    private String alamat;

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

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

}