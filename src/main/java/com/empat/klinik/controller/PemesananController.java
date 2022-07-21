package com.empat.klinik.controller;

import com.empat.klinik.model.dto.DefaultResponse;
//import com.empat.klinik.model.dto.IcdxDto;
import com.empat.klinik.model.dto.PemesananDetailDto;
import com.empat.klinik.model.dto.PemesananDto;
//import com.empat.klinik.model.entity.Icdx;
import com.empat.klinik.model.entity.Pemesanan;
import com.empat.klinik.repository.IcdxRepository;
import com.empat.klinik.repository.KaryawanRepository;
import com.empat.klinik.repository.PasienRepository;
import com.empat.klinik.repository.PemesananRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.sql.Date.*;
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
    @GetMapping("/tanggallayanan")
    public LocalDate getTanggalPelayanan(){
        return java.time.LocalDate.now();
    }
    @GetMapping("/listpemesanan")
    public List<PemesananDetailDto> getListPemesanan() {

        List<PemesananDetailDto> list = new ArrayList();
        for (Pemesanan i : pemesananRepository.findAll()) {
            list.add(convertEntityToDto(i));
        }
        return list;
    }



    public PemesananDetailDto convertEntityToDto(Pemesanan entity1) {
        Optional<Pemesanan> optionalPemesanan = pemesananRepository.findByIdPasienAndKdIcdxAndNik(entity1.getIdPasien(), entity1.getKdIcdx(), entity1.getNik());
        PemesananDetailDto dto = new PemesananDetailDto();
        if (optionalPemesanan.isPresent()) {
            Pemesanan pemesanan1 = optionalPemesanan.get();
            dto.setNoAntrian(pemesanan1.getIdPemesanan());
            dto.setNama(pemesanan1.getPasien().getNama());
            dto.setNamaIcdx(pemesanan1.getIcdx().getNamaIcdx());
            dto.setNamaKaryawan(pemesanan1.getKaryawan().getNamaKaryawan());
            dto.setStatusPelayanan(pemesanan1.getStatusPelayanan());
            if((pemesanan1.getStatusPelayanan()).equals("0")){
                dto.setStatusPelayanan("Belum Dilayani");
            }else if((pemesanan1.getStatusPelayanan()).equals("1")){
                dto.setStatusPelayanan("Sudah Dilayani");
            }else{
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
        Optional<Pemesanan> optionalIdPasien = pemesananRepository.findByIdPasien(pemesananDto.getIdPasien());
        if (optionalIdPasien.isPresent()) {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Data gagal disimpan, No RM sudah terdaftar");
        } else {
            pemesananRepository.save(pemesanan);
            df.setStatus(Boolean.TRUE);
            df.setData(pemesananDto);
            df.setPesan("Data Berhasil Disimpan");
        }
        return df;
    }

    public Pemesanan convertDtoToEntity(PemesananDto pemesananDto) {
        Pemesanan pemesanan = new Pemesanan();
        pemesanan.setIdPemesanan(pemesanan.getIdPemesanan());
        pemesanan.setNoAntrian(pemesanan.getIdPemesanan());
        pemesanan.setIdPasien(pemesananDto.getIdPasien());
        pemesanan.setKdIcdx(pemesananDto.getKdIcdx());
        pemesanan.setNik(pemesananDto.getNik());
        pemesanan.setStatusPelayanan(pemesananDto.getStatusPelayanan());
        pemesanan.setTanggalPesan(java.time.LocalDate.now());

        return pemesanan;
    }

    //Sub Fitur Delete Data
    @DeleteMapping("/delete/{idPasien}")
    public DefaultResponse deletById(@PathVariable Integer idPasien) {
        DefaultResponse df = new DefaultResponse();
        Optional<Pemesanan> optionalPemesanan = pemesananRepository.findByIdPasien(idPasien);
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
        Optional<Pemesanan> optionalPemesanan = pemesananRepository.findByNoAntrian(noAntrian);
        Pemesanan pemesanan = optionalPemesanan.get();
        if (optionalPemesanan.isPresent()) {
            //pemesanan.setNoAntrian(pemesananDto.getNoAntrian());
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