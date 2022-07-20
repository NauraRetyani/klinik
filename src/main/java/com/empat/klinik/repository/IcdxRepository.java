package com.empat.klinik.repository;

import com.empat.klinik.model.entity.Icdx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IcdxRepository extends JpaRepository<Icdx, String>{
    Optional<Icdx> findByKdIcdx(String kdIcdx);
    Optional<Icdx> deleteByKdIcdx(String kdIcdx);
}
