package com.empat.klinik.controller;

import com.empat.klinik.model.dto.DefaultResponse;
//import com.empat.klinik.model.dto.IcdxDto;
import com.empat.klinik.model.dto.PemesananDto;
//import com.empat.klinik.model.entity.Icdx;
import com.empat.klinik.model.entity.Pemesanan;
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

    @GetMapping("/listpemesanan")
    public List<PemesananDto> getListPemesanan() {
        List<PemesananDto> list = new ArrayList();
        for (Pemesanan i : pemesananRepository.findAll()) {
            list.add(convertEntityToDto(i));
        }
        return list;
    }

    public PemesananDto convertEntityToDto(Pemesanan entity) {
        PemesananDto dto = new PemesananDto();
        dto.setNoAntrian(entity.getNoAntrian());
        dto.setNamaPasien(entity.getNamaPasien());
        dto.setIcdx(entity.getIcdx());
        dto.setStatusPelayanan(entity.getStatusPelayanan());
        return dto;
    }
    @PostMapping("/savedata")
    public DefaultResponse<PemesananDto> savePemesanan(@RequestBody PemesananDto pemesananDto) {
        Pemesanan pemesanan = convertDtoToEntity(pemesananDto);
        DefaultResponse<PemesananDto> df = new DefaultResponse<>();
        Optional<Pemesanan> optionalIdPasien = pemesananRepository.findByIdPasien(pemesananDto.getNamaPasien());
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
        pemesanan.setNamaPasien(pemesananDto.getNamaPasien());
        pemesanan.setIcdx(pemesananDto.getIcdx());
        pemesanan.setStatusPelayanan(pemesananDto.getStatusPelayanan());

        return pemesanan;
    }
}
