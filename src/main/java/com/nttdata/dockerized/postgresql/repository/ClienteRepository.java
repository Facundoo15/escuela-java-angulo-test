package com.nttdata.dockerized.postgresql.repository;

import com.nttdata.dockerized.postgresql.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
    List<Cliente> findByActivoTrue();
    boolean existsByEmail(String email);
}
