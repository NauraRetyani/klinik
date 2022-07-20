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
    private Long nik;

    @Column(name = "nama", length = 30)
    private String namaKaryawan;

    @Column(name = "username", length = 15, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "no_telp", length = 13)
    private Long telp;

    @Column(name = "alamat", length = 50)
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