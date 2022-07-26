package com.empat.klinik.repository;

import com.empat.klinik.model.entity.Icdx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IcdxRepository extends JpaRepository<Icdx, String>{
    Optional<Icdx> findById(String kdIcdx);
    Optional<Icdx> deleteByKdIcdx(String kdIcdx);
    @Query(value = "SELECT * FROM t_icdx WHERE kd_icdx LIKE %?1% OR nama_icdx LIKE %?1%", nativeQuery = true)
    List<Icdx> search(String search);
}
