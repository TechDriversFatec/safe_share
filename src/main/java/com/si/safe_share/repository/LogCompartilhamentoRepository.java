package com.si.safe_share.repository;

import com.si.safe_share.model.LogCompartilhamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogCompartilhamentoRepository extends JpaRepository<LogCompartilhamento, Integer> {
    Optional<LogCompartilhamento> findById(Integer id);
}
