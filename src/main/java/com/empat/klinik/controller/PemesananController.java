package com.empat.klinik.controller;

import com.empat.klinik.model.dto.DefaultResponse;
//import com.empat.klinik.model.dto.IcdxDto;
import com.empat.klinik.model.dto.PemesananDetailDto;
import com.empat.klinik.model.dto.PemesananDto;
//import com.empat.klinik.model.entity.Icdx;
import com.empat.klinik.model.entity.Icdx;
import com.empat.klinik.model.entity.Pasien;
import com.empat.klinik.model.entity.Pemesanan;
import com.empat.klinik.repository.IcdxRepository;
import com.empat.klinik.repository.KaryawanRepository;
import com.empat.klinik.repository.PasienRepository;
import com.empat.klinik.repository.PemesananRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<PemesananDetailDto> getListPemesanan() {
        List<PemesananDetailDto> list = new ArrayList();
        for (Pemesanan i : pemesananRepository.findAll()) {
            list.add(convertEntityToDto(i));
        }
        return list;
    }

    public PemesananDetailDto convertEntityToDto(Pemesanan entity1) {
        Optional <Pemesanan> optionalPemesanan = pemesananRepository.findByIdPasienAndKdIcdx(entity1.getIdPasien(), entity1.getKdIcdx());
        PemesananDetailDto dto = new PemesananDetailDto();
        if(optionalPemesanan.isPresent()){
            Pemesanan pemesanan1 = optionalPemesanan.get();
            dto.setNoAntrian(pemesanan1.getNoAntrian());
            dto.setNama(pemesanan1.getPasien().getNama());
            dto.setNamaIcdx(pemesanan1.getIcdx().getNamaIcdx());
        }
        dto.setStatusPelayanan("Belum Dilayani");

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
        pemesanan.setNoAntrian(pemesananDto.getNoAntrian());
        pemesanan.setIdPasien(pemesananDto.getIdPasien());
        pemesanan.setKdIcdx(pemesananDto.getKdIcdx());
        //pemesanan.setNik(pemesananDto.getIdKaryawan());
        pemesanan.setStatusPelayanan(pemesananDto.getStatusPelayanan());

        return pemesanan;
    }

    //Delete Data
    //Masih error
    @DeleteMapping("/delete/{id_pasien}")
    public DefaultResponse deletById(@PathVariable("id_pasien") String id_pasien) {
        DefaultResponse df = new DefaultResponse();
        try {
            pemesananRepository.deleteByIdPasien(id_pasien);
            df.setStatus(Boolean.TRUE);
            df.setPesan("Data Berhasil Dihapus");
        } catch (Exception e) {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Data Gagal Dihapus");
        }
        return df;
    }
}
