package com.meutime.manager.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meutime.manager.entity.Campeonato;
import com.meutime.manager.repository.CampeonatoRepository;
import com.meutime.manager.service.exceptions.CampeonatoInUseException;
import com.meutime.manager.service.exceptions.CampeonatoNotFoundException;

@Service
public class CampeonatoService {
	private final CampeonatoRepository repository;

	public CampeonatoService(CampeonatoRepository repository) {
		this.repository = repository;
	}

	public List<Campeonato> listar() {
		return repository.findAll();
	}

	@Transactional
	public Campeonato salvar(Campeonato campeonato) {
		return repository.save(campeonato);
	}

	public Campeonato getById(Long id) {
		return repository.findById(id).orElseThrow(() -> new CampeonatoNotFoundException(id));
	}

	@Transactional
	public void deletar(Long id) {
		Campeonato campeonatoDeletado = getById(id);

		try {
			repository.deleteById(id);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new CampeonatoNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new CampeonatoInUseException(campeonatoDeletado.getNome(), id);
		}
	}

}