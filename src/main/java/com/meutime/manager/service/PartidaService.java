package com.meutime.manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meutime.manager.entity.Campeonato;
import com.meutime.manager.entity.Clube;
import com.meutime.manager.entity.Partida;
import com.meutime.manager.repository.CampeonatoRepository;
import com.meutime.manager.repository.ClubeRepository;
import com.meutime.manager.repository.PartidaRepository;

@Service
public class PartidaService {
	private final PartidaRepository repository;
	private final CampeonatoRepository campeonatoRepository;
	private final ClubeRepository clubeRepository;

	public PartidaService(PartidaRepository repository, CampeonatoRepository campeonatoRepository,
			ClubeRepository clubeRepository) {
		this.repository = repository;
		this.campeonatoRepository = campeonatoRepository;
		this.clubeRepository = clubeRepository;
	}

	public List<Partida> listar() {
		return repository.findAll();
	}

	public Partida salvar(Partida partida) {
		Clube storedClube = clubeRepository.findById(partida.getClube().getId())
				.orElseThrow(() -> new RuntimeException("Clube não encontrado"));

		Clube storedAdversario = clubeRepository.findById(partida.getAdversario().getId())
				.orElseThrow(() -> new RuntimeException("Adversário não encontrado"));

		Campeonato storedCampeonato = campeonatoRepository.findById(partida.getCampeonato().getId())
				.orElseThrow(() -> new RuntimeException("Campeonato não encontrado"));

		partida.setClube(storedClube);
		partida.setAdversario(storedAdversario);
		partida.setCampeonato(storedCampeonato);
		partida.definirResultado();

		Partida salva = repository.save(partida);
		return salva;
	}
}