package com.meutime.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meutime.manager.entity.Campeonato;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {
}