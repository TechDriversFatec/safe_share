package com.si.safe_share.repository;

import com.si.safe_share.model.LogCompartilhamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogCompartilhamentoRepository extends JpaRepository<LogCompartilhamento, Integer> { }
