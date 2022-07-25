package com.empat.klinik.controller;

import com.empat.klinik.model.dto.*;
import com.empat.klinik.model.entity.Pekerjaan;
import com.empat.klinik.model.entity.Pemesanan;
import com.empat.klinik.repository.PekerjaanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job")
public class PekerjaanController {
    @Autowired
    private PekerjaanRepository pekerjaanRepository;

    @GetMapping("/listpekerjaan")
    public List<JobDto> getListPekerjaan() {
        List<JobDto> list = new ArrayList();
        for (Pekerjaan i : pekerjaanRepository.findAll()) {
            list.add(convertEntityToDto(i));
        }
        return list;
    }

    public JobDto convertEntityToDto(Pekerjaan pekerjaan) {
        Optional<Pekerjaan> optionalPekerjaan = pekerjaanRepository.findById(pekerjaan.getIdJob());
        JobDto dto = new JobDto();
        if (optionalPekerjaan.isPresent()) {
            Pekerjaan pekerjaan1 = optionalPekerjaan.get();
            dto.setIdJob(pekerjaan1.getIdJob());
            dto.setNamaJob(pekerjaan1.getNamaJob());
        }
        return dto;
    }

    //Fitur tambah data
    @PostMapping("/savedata")
    public DefaultResponse<JobDto> savePemesanan(@RequestBody JobDto jobDto) {
        Pekerjaan pekerjaan = convertDtoToEntity(jobDto);
        DefaultResponse<JobDto> df = new DefaultResponse<>();
        Optional<Pekerjaan> optionalJob = pekerjaanRepository.findById(jobDto.getIdJob());
        if (optionalJob.isPresent()) {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Data gagal disimpan, id sudah terdaftar");
        } else {
            pekerjaanRepository.save(pekerjaan);
            df.setStatus(Boolean.TRUE);
            df.setData(jobDto);
            df.setPesan("Data Berhasil Disimpan");
        }
        return df;
    }

    public Pekerjaan convertDtoToEntity(JobDto jobDto) {
        Pekerjaan pekerjaan = new Pekerjaan();
        pekerjaan.setIdJob(jobDto.getIdJob());
        pekerjaan.setNamaJob(jobDto.getNamaJob());

        return pekerjaan;
    }

    //Sub Fitur Delete Data
    @DeleteMapping("/delete/{idJob}")
    public DefaultResponse deletById(@PathVariable Integer idJob) {
        DefaultResponse df = new DefaultResponse();
        Optional<Pekerjaan> optionalPekerjaan = pekerjaanRepository.findById(idJob);
        if (optionalPekerjaan.isPresent()) {
            pekerjaanRepository.delete(optionalPekerjaan.get());
            df.setStatus(Boolean.TRUE);
            df.setPesan("Data Berhasil Dihapus");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Data Tidak Ditemukan");
        }
        return df;
    }

    //Subfitur Update atau Edit
    @PutMapping("/update/{idJob}")
    public DefaultResponse update(@PathVariable Integer idJob, @RequestBody JobDto jobDto) {
        DefaultResponse df = new DefaultResponse();
        Optional<Pekerjaan> optionalJob = pekerjaanRepository.findById(idJob);
        Pekerjaan pekerjaan = optionalJob.get();
        if (optionalJob.isPresent()) {
            //pemesanan.setNoAntrian(pemesananDto.getNoAntrian());
            pekerjaan.setNamaJob(jobDto.getNamaJob());
            //pemesananRepository.save(pemesanan);
            df.setStatus(Boolean.TRUE);
            df.setData(jobDto);
            df.setPesan("Data Perubahan Berhasil Disimpan");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Id Tidak Ditemukan");
        }
        return df;
    }
}

