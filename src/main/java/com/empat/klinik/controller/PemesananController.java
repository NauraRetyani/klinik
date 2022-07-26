package com.empat.klinik.controller;

import com.empat.klinik.model.dto.DefaultResponse;
//import com.empat.klinik.model.dto.IcdxDto;
import com.empat.klinik.model.dto.PemesananDetailDto;
import com.empat.klinik.model.dto.PemesananDto;
//import com.empat.klinik.model.entity.Icdx;
import com.empat.klinik.model.entity.Pasien;
import com.empat.klinik.model.entity.Pemesanan;
import com.empat.klinik.repository.IcdxRepository;
import com.empat.klinik.repository.KaryawanRepository;
import com.empat.klinik.repository.PasienRepository;
import com.empat.klinik.repository.PemesananRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/pemesanan")
public class PemesananController {
    @Autowired
    private PemesananRepository pemesananRepository;
    @Autowired
    private PasienRepository pasienRepository;
    @Autowired
    private KaryawanRepository karyawanRepository;
    @Autowired
    private IcdxRepository icdxRepository;

    //Tampilan muka fitur Pemesanan

    @GetMapping("/listpemesanan")
    public ArrayList fitur() {
        ArrayList fitur = new ArrayList();
        int i = 0;
        while (i < 2) {
            fitur.add(0, "Tanggal Pelayanan " + getTanggalPelayanan());
            fitur.add(1, getListPemesanan());
            break;
        }
        return fitur;
    }

    public LocalDate getTanggalPelayanan() {
        return java.time.LocalDate.now();
    }

    public List<PemesananDetailDto> getListPemesanan() {
        List<PemesananDetailDto> list = new ArrayList();
        for (Pemesanan i : pemesananRepository.findByTanggalPesan()) {
            list.add(convertEntityToDto(i));
        }
        return list;
    }

    public PemesananDetailDto convertEntityToDto(Pemesanan entity1) {
        Optional<Pemesanan> optionalPemesanan = pemesananRepository.findByIdPasienAndKdIcdxAndNik(entity1.getIdPasien(), entity1.getKdIcdx(), entity1.getNik());
        PemesananDetailDto dto = new PemesananDetailDto();
        if (optionalPemesanan.isPresent()) {
            Pemesanan pemesanan1 = optionalPemesanan.get();
            dto.setNoAntrian(pemesanan1.getNoAntrian());
            dto.setNama(pemesanan1.getPasien().getNama());
            dto.setIdPasien(pemesanan1.getIdPasien());
            dto.setPekerjaan(pemesanan1.getPekerjaan().getNamaJob());
            dto.setNamaIcdx(pemesanan1.getIcdx().getNamaIcdx());
            dto.setNamaKaryawan(pemesanan1.getKaryawan().getNamaKaryawan());
            dto.setStatusPelayanan(pemesanan1.getStatusPelayanan());
            if ((pemesanan1.getStatusPelayanan()).equals("0")) {
                dto.setStatusPelayanan("Belum Dilayani");
            } else if ((pemesanan1.getStatusPelayanan()).equals("1")) {
                dto.setStatusPelayanan("Sudah Dilayani");
            } else {
                dto.setStatusPelayanan("Kode Status Tidak Valid");
            }
        }

        return dto;

    }

    //Fitur tambah data
    @PostMapping("/savedata")
    public DefaultResponse<PemesananDto> savePemesanan(@RequestBody PemesananDto pemesananDto) {
        Pemesanan pemesanan = convertDtoToEntity(pemesananDto);
        DefaultResponse<PemesananDto> df = new DefaultResponse<>();
        try {
            Optional<Pemesanan> optionalIdPasien = pemesananRepository.findByIdPasien(pemesananDto.getIdPasien());
            if (optionalIdPasien.isPresent() || !(optionalIdPasien.isPresent())) {
                pemesananRepository.save(pemesanan);
                df.setStatus(Boolean.TRUE);
                df.setData(pemesananDto);
                df.setPesan("Data Berhasil Disimpan");
            }
        } catch (Exception e) {
            if (pemesanan.getTanggalPesan().equals(java.time.LocalDate.now())) {
                df.setStatus(Boolean.FALSE);
                df.setPesan("Data gagal disimpan, No RM sudah terdaftar hari ini");
            } else {
                df.setStatus(Boolean.FALSE);
                df.setPesan("Data gagal disimpan, Tidak boleh melakukan Booking pemesanan untuk lain hari ");
            }

        }
        return df;
    }

    public Pemesanan convertDtoToEntity(PemesananDto pemesananDto) {
        Integer totalPemesanan = pemesananRepository.countPemesanan().intValue();
        if (totalPemesanan == null) {
            totalPemesanan = 0;
        }
        Integer noAntrian = totalPemesanan + 1;

        Optional<Pasien> optionalPasien = pasienRepository.findById(pemesananDto.getIdPasien());
        Pasien pasien = optionalPasien.get();

        Pemesanan pemesanan = new Pemesanan();
        pemesanan.setNoAntrian(noAntrian);
        pemesanan.setIdPasien(pemesananDto.getIdPasien());
        pemesanan.setKdIcdx(pemesananDto.getKdIcdx());
        pemesanan.setNik(pemesananDto.getNik());
        pemesanan.setStatusPelayanan("0");
        pemesanan.setTanggalPesan(pemesananDto.getTanggalPesan());
        try{
            if (pemesananDto.getTanggalPesan().equals("") || pemesananDto.getTanggalPesan().equals(java.time.LocalDate.now())) {
            } else {
                pemesanan.setTanggalPesan(pemesananDto.getTanggalPesan());
            }
        }catch(Exception e){
            pemesanan.setTanggalPesan(java.time.LocalDate.now());
        }
        pemesanan.setNama(pasien.getNama());
        pemesanan.setIdPekerjaan(pasien.getIdJob());

        return pemesanan;
    }

    //Sub Fitur Delete Data
    @DeleteMapping("/delete/{idPasien}")
    public DefaultResponse deletById(@PathVariable Integer idPasien) {
        DefaultResponse df = new DefaultResponse();
        Optional<Pemesanan> optionalPemesanan = pemesananRepository.findByIdPasienAndTanggalPesan(idPasien,LocalDate.now());
        if (optionalPemesanan.isPresent()) {
            pemesananRepository.delete(optionalPemesanan.get());
            df.setStatus(Boolean.TRUE);
            df.setPesan("Data Berhasil Dihapus");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Data Tidak Ditemukan");
        }
        return df;
    }

    //Subfitur Update atau Edit
    @PutMapping("/update/{noAntrian}")
    public DefaultResponse update(@PathVariable Integer noAntrian, @RequestBody PemesananDto pemesananDto) {
        DefaultResponse df = new DefaultResponse();
        Optional<Pemesanan> optionalPemesanan = pemesananRepository.findByNoAntrianAndTanggalPesan(noAntrian,LocalDate.now());
        Pemesanan pemesanan = optionalPemesanan.get();
        if (optionalPemesanan.isPresent()) {
            pemesanan.setStatusPelayanan(pemesananDto.getStatusPelayanan());
            pemesananRepository.save(pemesanan);
            df.setStatus(Boolean.TRUE);
            df.setData(pemesananDto);
            df.setPesan("Data Perubahan Berhasil Disimpan");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setPesan("No Antrian Tidak Ditemukan");
        }
        return df;
    }
}