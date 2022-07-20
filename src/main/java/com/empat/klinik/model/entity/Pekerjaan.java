package com.empat.klinik.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_pekerjaan")
public class Pekerjaan {
    @Id
    @Column(name = "id_pekerjaan")
    private String idJob;
    @Column(name = "nama_pekerjaan")
    private String namaJob;

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getNamaJob() {
        return namaJob;
    }

    public void setNamaJob(String namaJob) {
        this.namaJob = namaJob;
    }
}
