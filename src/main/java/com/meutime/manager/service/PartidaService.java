package com.meutime.manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meutime.manager.entity.Partida;
import com.meutime.manager.repository.PartidaRepository;

@Service
public class PartidaService {
	private final PartidaRepository repository;

	public PartidaService(PartidaRepository repository) {
		this.repository = repository;
	}

	public List<Partida> listar() {
		return repository.findAll();
	}

	public Partida salvar(Partida partida) {
		return repository.save(partida);
	}
}