package com.empat.klinik.controller;

import com.empat.klinik.model.dto.DefaultResponse;
import com.empat.klinik.model.dto.KaryawanDto;
import com.empat.klinik.model.dto.PemesananDetailDto;
import com.empat.klinik.model.dto.PemesananDto;
import com.empat.klinik.model.entity.Karyawan;
import com.empat.klinik.model.entity.Pemesanan;
import com.empat.klinik.repository.IcdxRepository;
import com.empat.klinik.repository.KaryawanRepository;
import com.empat.klinik.repository.PasienRepository;
import com.empat.klinik.repository.PemesananRepository;
import com.empat.klinik.model.entity.Karyawan;
import com.empat.klinik.repository.KaryawanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/karyawan")
public class KaryawanController {

    @Autowired
    private KaryawanRepository karyawanRepository;

    //Tampilan muka fitur Karyawan
    @GetMapping("/listkaryawan")
    public List<KaryawanDto> getListKaryawan() {
        List<KaryawanDto> list = new ArrayList();
        for (Karyawan i : karyawanRepository.findAll()) {
            list.add(convertEntityToDto(i));
        }
        return list;
    }

    public KaryawanDto convertEntityToDto(Karyawan karyawan) {
        Optional<Karyawan> optionalKaryawan = karyawanRepository.findById(karyawan.getNik());
        KaryawanDto dto = new KaryawanDto();
        if (optionalKaryawan.isPresent()) {
            Karyawan karyawan1 = optionalKaryawan.get();
            dto.setNik(karyawan1.getNik());
            dto.setNamaKaryawan(karyawan1.getNamaKaryawan());
            dto.setUsername(karyawan1.getUsername());
            dto.setPassword(karyawan1.getPassword());
            dto.setTelp(karyawan1.getTelp());
            dto.setAlamat(karyawan1.getAlamat());
        }

        return dto;

    }

    //Sub fitur cek karyawan (Pencarian Karyawan Berdasarkan Nik)
    //need rev//
    @GetMapping("/byid/{nik}")
    public DefaultResponse getByIdKaryawan(@PathVariable Long nik) {
        DefaultResponse df = new DefaultResponse();
        Optional<Karyawan> namaKaryawan = karyawanRepository.findById(nik);
        if (namaKaryawan.isPresent()) {
            df.setStatus(Boolean.TRUE);
            df.setPesan("Data Ditemukan");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Data Tidak Ada");
        }
        return df;
    }

    //Sub-Fitur tambah data
    @PostMapping("/savedata")
    public DefaultResponse<KaryawanDto> saveKaryawan(@RequestBody KaryawanDto karyawanDto) {
        Karyawan karyawan = convertDtoToEntity(karyawanDto);
        DefaultResponse<KaryawanDto> df = new DefaultResponse<>();
        Optional<Karyawan> optionalKaryawan = karyawanRepository.findById(karyawanDto.getNik());
        if (optionalKaryawan.isPresent()) {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Data gagal disimpan, NIK sudah terdaftar");
        } else {
            karyawanRepository.save(karyawan);
            df.setStatus(Boolean.TRUE);
            df.setData(karyawanDto);
            df.setPesan("Data Berhasil Disimpan");
        }
        return df;
    }

    public Karyawan convertDtoToEntity(KaryawanDto karyawanDto) {
        Karyawan karyawan = new Karyawan();
        karyawan.setNik(karyawanDto.getNik());
        karyawan.setNamaKaryawan(karyawanDto.getNamaKaryawan());
        karyawan.setUsername(karyawanDto.getUsername());
        karyawan.setPassword(karyawanDto.getPassword());
        karyawan.setTelp(karyawanDto.getTelp());
        karyawan.setAlamat(karyawanDto.getAlamat());

        return karyawan;
    }

    //Sub Fitur Delete Data
    @DeleteMapping("/delete/{nik}")
    public DefaultResponse deletById(@PathVariable Long nik) {
        DefaultResponse df = new DefaultResponse();
        Optional<Karyawan> optionalKaryawan = karyawanRepository.findById(nik);
        if (optionalKaryawan.isPresent()) {
            karyawanRepository.delete(optionalKaryawan.get());
            df.setStatus(Boolean.TRUE);
            df.setPesan("Data Berhasil Dihapus");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Data Tidak Ditemukan");
        }
        return df;
    }

    //Subfitur Update atau Edit
    @PutMapping("/update/{nik}")
    public DefaultResponse update(@PathVariable Long nik, @RequestBody KaryawanDto karyawanDto) {
        DefaultResponse df = new DefaultResponse();
        Optional<Karyawan> optionalKaryawan = karyawanRepository.findById(nik);
        Karyawan karyawan = optionalKaryawan.get();
        if (optionalKaryawan.isPresent()) {
            karyawan.setNamaKaryawan(karyawanDto.getNamaKaryawan());
            karyawan.setUsername(karyawanDto.getUsername());
            karyawan.setPassword(karyawanDto.getPassword());
            karyawan.setTelp(karyawanDto.getTelp());
            karyawan.setAlamat(karyawanDto.getAlamat());
            karyawanRepository.save(karyawan);
            df.setStatus(Boolean.TRUE);
            df.setData(karyawanDto);
            df.setPesan("Data Perubahan Berhasil Disimpan");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setPesan("NIK Tidak Ditemukan");
        }
        return df;

    }
}
