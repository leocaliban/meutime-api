package com.meutime.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.meutime.manager.entity.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
	@Query("SELECT p FROM Partida p WHERE p.clube.id = :clubeId AND p.adversario.id = :adversarioId")
	List<Partida> findConfrontosContra(@Param("clubeId") Long clubeId, @Param("adversarioId") Long adversarioId);

}