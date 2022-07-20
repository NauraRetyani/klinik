package com.empat.klinik.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_icdx")
public class Icdx {
    @Id
    @Column(name = "kd_icdx", length = 6)
    private String kdIcdx;

    @Column(name = "nama_icdx", length = 75)
    private String namaIcdx;

    public String getKdIcdx() {
        return kdIcdx;
    }

    public void setKdIcdx(String kdIcdx) {
        this.kdIcdx = kdIcdx;
    }

    public String getNamaIcdx() {
        return namaIcdx;
    }

    public void setNamaIcdx(String namaIcdx) {
        this.namaIcdx = namaIcdx;
    }
}
