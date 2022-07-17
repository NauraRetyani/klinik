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
    @Autowired
    private IcdxRepository icdxRepository;

    @GetMapping("/getdata")
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
    @PostMapping("/savedata")
    public DefaultResponse<IcdxDto> saveIcdx(@RequestBody IcdxDto icdxDto) {
        Icdx icdx = convertDtoToEntity(icdxDto);
        DefaultResponse<IcdxDto> df = new DefaultResponse<>();
        Optional<Icdx> optionalKdIcdx = icdxRepository.findByKdIcdx(icdxDto.getKdIcdx());
        if (optionalKdIcdx.isPresent()) {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Data gagal disimpan, No RM sudah terdaftar");
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


}
