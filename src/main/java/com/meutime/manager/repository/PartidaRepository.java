package com.meutime.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meutime.manager.entity.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
}