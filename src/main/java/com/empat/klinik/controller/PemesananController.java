package com.empat.klinik.controller;

import com.empat.klinik.model.dto.DefaultResponse;
//import com.empat.klinik.model.dto.IcdxDto;
import com.empat.klinik.model.dto.PemesananDetailDto;
import com.empat.klinik.model.dto.PemesananDto;
//import com.empat.klinik.model.entity.Icdx;
import com.empat.klinik.model.dto.projection.GetNextAntrian;
import com.empat.klinik.model.dto.projection.Laporan;
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
        while (i == 0) {
            fitur.add(0, "Tanggal Pelayanan " + getTanggalPelayanan());
            try {
                fitur.add(1, getListPemesanan());
                break;
            } catch (Exception e) {
                fitur.add(1, "Tidak Bisa Menampilkan List " +
                        "Terdapat Riwayat nilai Unique yang pernah terekam di hari " +
                        "yang sama");
                break;
            }
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
        PemesananDetailDto dto = new PemesananDetailDto();
        dto.setNoAntrian(entity1.getNoAntrian());
        dto.setNama(entity1.getNama());
        dto.setIdPasien(entity1.getIdPasien());
        if(pemesananRepository.existsById(entity1.getIdPasien())){
            dto.setPekerjaan(entity1.getPekerjaan().getNamaJob());
            dto.setNamaIcdx(entity1.getIcdx().getNamaIcdx());
            dto.setNamaKaryawan(entity1.getKaryawan().getNamaKaryawan());
            dto.setStatusPelayanan(entity1.getStatusPelayanan());
            if ((entity1.getStatusPelayanan()).equals("0")) {
                dto.setStatusPelayanan("Belum Dilayani");
            } else if ((entity1.getStatusPelayanan()).equals("1")) {
                dto.setStatusPelayanan("Sudah Dilayani");
            } else {
                dto.setStatusPelayanan("Kode Status Tidak Valid");
            }
        }else if(!(pemesananRepository.existsById(entity1.getIdPasien()))){
            dto.setPekerjaan(entity1.getPekerjaan().getNamaJob());
            dto.setNamaIcdx(entity1.getIcdx().getNamaIcdx());
            dto.setNamaKaryawan(entity1.getKaryawan().getNamaKaryawan());
            dto.setStatusPelayanan(entity1.getStatusPelayanan());
            if ((entity1.getStatusPelayanan()).equals("0")) {
                dto.setStatusPelayanan("Belum Dilayani");
            } else if ((entity1.getStatusPelayanan()).equals("1")) {
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
            //Save data jika pasien baru pertama kali berobat/melakukan pemesanan
            if (!(optionalIdPasien.isPresent())) {
                //Memeriksa bahwa pemesanan hanya untuk di hari tersebut!!!
                if (!(pemesanan.getTanggalPesan().equals(java.time.LocalDate.now()))) {
                    df.setStatus(Boolean.FALSE);
                    df.setPesan("Data gagal disimpan, Tidak boleh melakukan Booking/Pemesanan untuk lain hari ");
                } else {
                    pemesananRepository.save(pemesanan);
                    df.setStatus(Boolean.TRUE);
                    df.setData(pemesananDto);
                    df.setPesan("Data Berhasil Disimpan");
                }
            }
            //Save data jika pasien sudah lebih dari satu kali berobat/melakukan pemesanan
            else {
                //Memastikan bahwa pemesanan hanya untuk di hari tersebut!!!
                if (pemesanan.getTanggalPesan().equals(java.time.LocalDate.now())) {
                    //Memastikan tidak ada duplikat data!!!
                    Optional<Pemesanan> opTionalIdPasienAndTanggal = pemesananRepository.findByIdPasienAndTanggalPesan(pemesananDto.getIdPasien(), LocalDate.now());
                    if (opTionalIdPasienAndTanggal.isPresent()) {
                        df.setStatus(Boolean.FALSE);
                        df.setPesan("Data gagal disimpan, No RM sudah terdaftar hari ini");
                    }
                    //Memastikan data tersebut akan tersimpan jika tidak ditemukan duplikasi data!!!
                    else {
                        df.setStatus(Boolean.TRUE);
                        pemesananRepository.save(pemesanan);
                        df.setStatus(Boolean.TRUE);
                        df.setData(pemesananDto);
                        df.setPesan("Data Berhasil Disimpan");
                    }
                }
                //Memastikan bahwa data pasien tidak akan tersimpan jika selain untuk hari ini!!!
                else {
                    df.setStatus(Boolean.FALSE);
                    df.setPesan("Data gagal disimpan, Tidak boleh melakukan Booking pemesanan untuk lain hari ");
                }
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
        try {
            if (pemesananDto.getTanggalPesan().equals("") || pemesananDto.getTanggalPesan().equals(java.time.LocalDate.now())) {
            } else {
                pemesanan.setTanggalPesan(pemesananDto.getTanggalPesan());
            }
        } catch (Exception e) {
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
        Optional<Pemesanan> optionalPemesanan = pemesananRepository.findByIdPasienAndTanggalPesan(idPasien, LocalDate.now());
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
        Optional<Pemesanan> optionalPemesanan = pemesananRepository.findByNoAntrianAndTanggalPesan(noAntrian, LocalDate.now());
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

    //Subfitur Search Still error
    @GetMapping("/search/{search}")
    public List<PemesananDetailDto> cari(@PathVariable String search) {
        //DefaultResponse df = new DefaultResponse();
        List<PemesananDetailDto> searchPemesanan = new ArrayList();
        for (Pemesanan pemesanan : pemesananRepository.search(search, LocalDate.now())) {
            searchPemesanan.add(convertEntityToDto(pemesanan));
        }
        return searchPemesanan;
    }

    @GetMapping("/laporanprojection")
    public List<Laporan> nextNoAntrian() {
        List<Laporan> list = pemesananRepository.laporan();
        return list;
    }
}