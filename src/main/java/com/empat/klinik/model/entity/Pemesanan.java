package com.empat.klinik.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_pemesanan")
public class Pemesanan {
    @Id
    @Column (name = "no_antrian")
    private String noAntrian;

    @Column(name = "id_pasien")
    private String idPasien;
    @OneToOne
    @JoinColumn(name ="id_pasien", insertable = false, updatable = false)
    private Pasien pasien;
    @Column(name = "id_penyakit")
    private String kdIcdx;
    @OneToOne
    @JoinColumn(name ="id_penyakit", insertable = false, updatable = false)
    private Icdx icdx;

    @Column (name = "id_status_pelayanan")
    private String statusPelayanan;

    public String getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(String noAntrian) {
        this.noAntrian = noAntrian;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getKdIcdx() {
        return kdIcdx;
    }

    public void setKdIcdx(String kdIcdx) {
        this.kdIcdx = kdIcdx;
    }

    public String getStatusPelayanan() {
        return statusPelayanan;
    }

    public void setStatusPelayanan(String statusPelayanan) {
        this.statusPelayanan = statusPelayanan;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    public Icdx getIcdx() {
        return icdx;
    }

    public void setIcdx(Icdx icdx) {
        this.icdx = icdx;
    }
}
