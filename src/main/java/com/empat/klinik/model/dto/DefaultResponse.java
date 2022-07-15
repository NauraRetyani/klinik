package com.empat.klinik.model.dto;

public class DefaultResponse {
    private Boolean status;
    private String pesan;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

}
