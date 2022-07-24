package com.empat.klinik.controller;

import com.empat.klinik.model.dto.PemesananDetailDto;
import com.empat.klinik.model.entity.Pemesanan;
import com.empat.klinik.repository.PemesananRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/laporan")

public class LaporanController {
    @Autowired
    private PemesananRepository pemesananRepository;

    @GetMapping("/listlaporan")
    public List<PemesananDetailDto> getListLaporan() {
        List<PemesananDetailDto> list = new ArrayList();
        for (Pemesanan i : pemesananRepository.findStatusDone()) {
            list.add(convertEntityToDto(i));
        }
        return list;
    }

    public PemesananDetailDto convertEntityToDto(Pemesanan entity) {
        PemesananDetailDto dto = new PemesananDetailDto();
        dto.setNoAntrian(entity.getIdPemesanan());
        dto.setIdPasien(entity.getIdPasien());
        dto.setNama(entity.getPasien().getNama());
        dto.setStatusPelayanan(entity.getStatusPelayanan());

        return dto;
    }
}

