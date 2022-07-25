package com.empat.klinik.controller;

import com.empat.klinik.model.dto.DefaultResponse;
import com.empat.klinik.model.dto.KaryawanDto;
import com.empat.klinik.model.entity.Karyawan;
import com.empat.klinik.repository.KaryawanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/edit")
public class ChangePassword {
    @Autowired
    private KaryawanRepository karyawanRepository;

    @PutMapping("/password/{username}")
    public DefaultResponse update(@PathVariable String username, @RequestBody KaryawanDto karyawanDto) {
        DefaultResponse df = new DefaultResponse();
        Optional<Karyawan> optionalKaryawan = karyawanRepository.findByUsername(username);
        Karyawan karyawan = optionalKaryawan.get();

        karyawan.setPassword(karyawanDto.getPassword());

        String registerPassword = karyawanDto.getPassword();
        String retypePassword = karyawanDto.getConfirmPassword();

        if (registerPassword.equals(retypePassword)) {
            karyawanRepository.save(karyawan);
            df.setStatus(Boolean.TRUE);
            df.setData(username);
            df.setPesan("Password Berhasil Disimpan");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setPesan("Not Match");
        }
        return df;
    }
}
