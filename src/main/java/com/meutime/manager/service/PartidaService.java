package com.meutime.manager.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meutime.manager.dto.request.PartidaRequestDTO;
import com.meutime.manager.dto.response.ClubeResponseDTO;
import com.meutime.manager.dto.response.ConfrontoResponseDTO;
import com.meutime.manager.dto.response.PartidaResponseDTO;
import com.meutime.manager.entity.Campeonato;
import com.meutime.manager.entity.Clube;
import com.meutime.manager.entity.Partida;
import com.meutime.manager.entity.enums.Resultado;
import com.meutime.manager.mappers.GeneralMapper;
import com.meutime.manager.repository.CampeonatoRepository;
import com.meutime.manager.repository.PartidaRepository;
import com.meutime.manager.service.exceptions.PartidaNotFoundException;

@Service
public class PartidaService {
	private final PartidaRepository repository;
	private final CampeonatoRepository campeonatoRepository;
	private final ClubeService clubeService;

	private GeneralMapper<Partida, PartidaRequestDTO, PartidaResponseDTO> partidaMapper;
	private GeneralMapper<Clube, Void, ClubeResponseDTO> clubeMapper;

	public PartidaService(PartidaRepository repository, CampeonatoRepository campeonatoRepository,
			ClubeService clubeService, GeneralMapper<Partida, PartidaRequestDTO, PartidaResponseDTO> partidaMapper,
			GeneralMapper<Clube, Void, ClubeResponseDTO> clubeMapper) {
		this.repository = repository;
		this.campeonatoRepository = campeonatoRepository;
		this.clubeService = clubeService;
		this.partidaMapper = partidaMapper;
		this.clubeMapper = clubeMapper;
	}

	public List<Partida> listar() {
		return repository.findAll();
	}

	@Transactional
	public Partida salvar(Partida partida) {
		Clube storedClube = clubeService.getById(partida.getClube().getId());
		Clube storedAdversario = clubeService.getById(partida.getAdversario().getId());

		Campeonato storedCampeonato = campeonatoRepository.findById(partida.getCampeonato().getId())
				.orElseThrow(() -> new RuntimeException("Campeonato não encontrado"));

		partida.setClube(storedClube);
		partida.setAdversario(storedAdversario);
		partida.setCampeonato(storedCampeonato);
		partida.definirResultado();

		Partida salva = repository.save(partida);
		return salva;
	}

	public Partida getById(Long id) {
		return repository.findById(id).orElseThrow(() -> new PartidaNotFoundException(id));
	}

	public ConfrontoResponseDTO buscarConfrontoContra(Long clubeId, Long adversarioId) {
		Clube storedAdversario = clubeService.getById(adversarioId);
		List<Partida> confrontos = repository.findConfrontosContra(clubeId, adversarioId);
		int vitorias = 0, derrotas = 0, empates = 0, comPenaltis = 0;

		for (Partida p : confrontos) {
			if (p.getResultado() == Resultado.VITORIA)
				vitorias++;
			else if (p.getResultado() == Resultado.DERROTA)
				derrotas++;
			else
				empates++;

			if (p.getTevePenaltis())
				comPenaltis++;
		}

		List<PartidaResponseDTO> lista = partidaMapper.toResponseDTOList(confrontos, PartidaResponseDTO.class);

		ClubeResponseDTO adversarioResponse = clubeMapper.toResponseDTO(storedAdversario, ClubeResponseDTO.class);
		ConfrontoResponseDTO confrontoResponse = ConfrontoResponseDTO.builder().adversario(adversarioResponse)
				.vitorias(vitorias).derrotas(derrotas).empates(empates).partidasComPenaltis(comPenaltis).partidas(lista)
				.build();

		return confrontoResponse;
	}
}