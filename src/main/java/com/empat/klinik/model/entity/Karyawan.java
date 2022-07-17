package com.empat.klinik.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_karyawan")
public class Karyawan {
    @Id
    @Column(name = "nik", length = 16)
    private Integer nik;

    @Column(name = "nama", length = 30)
    private String nama;

    @Column(name = "username", length = 15, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "no_telp", length = 13)
    private Integer telp;

    @Column(name = "alamat", length = 50)
    private String alamat;

    public Integer getNik() {
        return nik;
    }

    public void setNik(Integer nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public Integer getTelp() {
        return telp;
    }

    public void setTelp(Integer telp) {
        this.telp = telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}