package com.empat.klinik.repository;

import com.empat.klinik.model.entity.Icdx;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IcdxRepository extends JpaRepository<Icdx, Long> {
    Optional<Icdx> findByKdIcdx(String kdIcdx);
}
