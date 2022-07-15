package com.empat.klinik.repository;

import com.empat.klinik.model.entity.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KaryawanRepository extends JpaRepository<Karyawan, String> {
    Optional<Karyawan> findByUsername(String username);

    Optional<Karyawan> findByPassword(String password);
}
