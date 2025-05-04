package com.meutime.manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meutime.manager.entity.Campeonato;
import com.meutime.manager.repository.CampeonatoRepository;

@Service
public class CampeonatoService {
	private final CampeonatoRepository repository;

	public CampeonatoService(CampeonatoRepository repository) {
		this.repository = repository;
	}

	public List<Campeonato> listar() {
		return repository.findAll();
	}

	public Campeonato salvar(Campeonato campeonato) {
		return repository.save(campeonato);
	}
}