package com.empat.klinik.controller;


import com.empat.klinik.model.dto.DefaultResponse;
import com.empat.klinik.model.dto.IcdxDto;
import com.empat.klinik.model.entity.Icdx;
import com.empat.klinik.repository.IcdxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/icdx")
public class IcdxController {

    private final IcdxRepository icdxRepository;

    public IcdxController(IcdxRepository icdxRepository) {
        this.icdxRepository = icdxRepository;
    }

    @GetMapping("/listicdx")
    public List<IcdxDto> getListIcdx() {
        List<IcdxDto> list = new ArrayList();
        for (Icdx i : icdxRepository.findAll()) {
            list.add(convertEntityToDto(i));
        }
        return list;
    }

    public IcdxDto convertEntityToDto(Icdx entity) {
        IcdxDto dto = new IcdxDto();
        dto.setKdIcdx(entity.getKdIcdx());
        dto.setNamaIcdx(entity.getNamaIcdx());
        return dto;
    }

    //Bug
    @PostMapping("/savedata")
    public DefaultResponse<IcdxDto> saveIcdx(@RequestBody IcdxDto icdxDto) {
        Icdx icdx = convertDtoToEntity(icdxDto);
        DefaultResponse<IcdxDto> df = new DefaultResponse<>();
        Optional<Icdx> optionalKdIcdx = icdxRepository.findById(icdxDto.getKdIcdx());
        if (optionalKdIcdx.isPresent()) {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Data gagal disimpan, kode Icdx sudah terdaftar");
        } else {
            icdxRepository.save(icdx);
            df.setStatus(Boolean.TRUE);
            df.setData(icdxDto);
            df.setPesan("Data Berhasil Disimpan");
        }
        return df;
    }

    public Icdx convertDtoToEntity(IcdxDto icdxDto) {
        Icdx icdx = new Icdx();
        icdx.setKdIcdx(icdxDto.getKdIcdx());
        icdx.setNamaIcdx(icdxDto.getNamaIcdx());
        return icdx;
    }

    @DeleteMapping("/delete/{kd_icdx}")
    public DefaultResponse deleteById(@PathVariable("kd_icdx") String kdIcdx) {
        DefaultResponse df = new DefaultResponse();
        Optional<Icdx> optionalIcdx = icdxRepository.findById(kdIcdx);
        if (optionalIcdx.isPresent()) {
            icdxRepository.delete(optionalIcdx.get());
            df.setStatus(Boolean.TRUE);
            df.setPesan("Data Berhasil Dihapus");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Data Tidak Ditemukan");
        }
//        try {
//            icdxRepository.deleteByKdIcdx(kdIcdx);
//            df.setStatus(Boolean.TRUE);
//            df.setPesan("Data Berhasil Dihapus");
//        } catch (Exception e) {
//            df.setStatus(Boolean.FALSE);
//            df.setPesan("Data Gagal Dihapus");
//        }
        return df;
    }

    @PutMapping("/update/{kd_icdx}")
    public DefaultResponse update(@PathVariable("kd_icdx") String kdIcdx, @RequestBody IcdxDto icdxDto) {
        DefaultResponse df = new DefaultResponse();
        Optional<Icdx> optionalKdIcdx = icdxRepository.findById(kdIcdx);
        Icdx icdx = optionalKdIcdx.get();
        if (optionalKdIcdx.isPresent()) {
            icdx.setNamaIcdx(icdxDto.getNamaIcdx());
            icdxRepository.save(icdx);
            df.setStatus(Boolean.TRUE);
            df.setData(icdxDto);
            df.setPesan("Data Berhasil Disimpan");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Kode Icdx Sudah Terdaftar");
        }
        return df;
    }

//    Search error 1 result
    @GetMapping("/search/{search}")
    public List<IcdxDto> search(@PathVariable String search) {
        List<IcdxDto> list = new ArrayList();
        for (Icdx i : icdxRepository.search(search)) {
            list.add(convertEntityToDto(i));
        }
        return list;
    }
}
