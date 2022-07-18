package com.empat.klinik.controller;

import com.empat.klinik.model.dto.DefaultResponse;
import com.empat.klinik.model.dto.PasienDto;
import com.empat.klinik.model.dto.PekerjaanDto;
import com.empat.klinik.model.entity.Pasien;
import com.empat.klinik.model.entity.Pekerjaan;
import com.empat.klinik.repository.PasienRepository;
import com.empat.klinik.repository.PekerjaanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pasien")
public class PasienController {
    @Autowired
    private PasienRepository pasienRepository;
    private PekerjaanRepository pekerjaanRepository;

//    public PasienController(PasienRepository pasienRepository, PekerjaanRepository pekerjaanRepository) {
//        this.pasienRepository = pasienRepository;
//        this.pekerjaanRepository = pekerjaanRepository;
//    }


    @GetMapping("/listpasien")
    public List<PasienDto> getListPasien() {
        List<PasienDto> list = new ArrayList();
        for (Pasien i : pasienRepository.findAll()) {
            list.add(convertEntityToDto(i));
        }
        return list;
    }

    @GetMapping("/job/{idPasien}")
    public PekerjaanDto getListPasien(@PathVariable Long idPasien) {
        Optional<Pasien> optionalPasien = pasienRepository.findById(idPasien);
        PekerjaanDto dto = new PekerjaanDto();
        if (optionalPasien.isPresent()) {
            Pasien pasien = optionalPasien.get();
            dto.setNama(pasien.getNama());
            dto.setNamaJob(pasien.getPekerjaan().getNamaJob());
        }
        return dto;
    }

    @PostMapping("/savedata")
    public DefaultResponse<PasienDto> savePasien(@RequestBody PasienDto pasienDto) {
        Pasien pasien = convertDtoToEntity(pasienDto);
        DefaultResponse<PasienDto> df = new DefaultResponse<>();
        Optional<Pasien> optionalIdPasien = pasienRepository.findById(pasienDto.getIdPasien());
        if (optionalIdPasien.isPresent()) {
            df.setStatus(Boolean.FALSE);
            df.setPesan("data gagal disimpan, pasien sudah terdaftar");
        } else {
            pasienRepository.save(pasien);
            df.setStatus(Boolean.TRUE);
            df.setData(pasienDto);
            df.setPesan("data berhasil disimpan");
        }
        return df;
    }

    public PasienDto convertEntityToDto(Pasien entity) {
        PasienDto dto = new PasienDto();
        dto.setIdPasien(entity.getIdPasien());
        dto.setNama(entity.getNama());
        dto.setGender(entity.getGender());
        dto.setBday(entity.getBday());
        dto.setGolDar(entity.getGolDar());
        dto.setAlamat(entity.getAlamat());
        return dto;
    }

    public Pasien convertDtoToEntity(PasienDto pasienDto) {
        Pasien pasien = new Pasien();

        pasien.setIdPasien(pasienDto.getIdPasien());
        pasien.setNama(pasienDto.getNama());
        pasien.setGender(pasienDto.getGender());
        pasien.setBday(pasienDto.getBday());
        pasien.setGolDar(pasienDto.getGolDar());
        pasien.setAlamat(pasienDto.getAlamat());

        return pasien;
    }
}

