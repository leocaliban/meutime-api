package com.meutime.manager.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meutime.manager.entity.Clube;
import com.meutime.manager.repository.ClubeRepository;
import com.meutime.manager.service.exceptions.ClubeInUseException;
import com.meutime.manager.service.exceptions.ClubeNotFoundException;

@Service
public class ClubeService {
	private final ClubeRepository repository;

	public ClubeService(ClubeRepository repository) {
		this.repository = repository;
	}

	public Clube getById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ClubeNotFoundException(id));
	}

	public List<Clube> getAll() {
		return repository.findAll();
	}

	public Clube salvar(Clube clube) {
		return repository.save(clube);
	}

	@Transactional
	public void deletar(Long id) {
		Clube clubeDeletado = getById(id);

		try {
			repository.deleteById(id);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ClubeNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new ClubeInUseException(clubeDeletado.getNome(), id);
		}
	}
}