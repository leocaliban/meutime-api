package com.meutime.manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meutime.manager.entity.Clube;
import com.meutime.manager.repository.ClubeRepository;

@Service
public class ClubeService {
	private final ClubeRepository repository;

	public ClubeService(ClubeRepository repository) {
		this.repository = repository;
	}

	public List<Clube> listar() {
		return repository.findAll();
	}

	public Clube salvar(Clube clube) {
		return repository.save(clube);
	}
}