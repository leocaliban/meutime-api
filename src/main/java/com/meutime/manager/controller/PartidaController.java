package com.meutime.manager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meutime.manager.dto.request.PartidaRequestDTO;
import com.meutime.manager.dto.response.ConfrontoResponseDTO;
import com.meutime.manager.dto.response.PartidaResponseDTO;
import com.meutime.manager.entity.Partida;
import com.meutime.manager.mappers.GeneralMapper;
import com.meutime.manager.service.PartidaService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/partidas")
public class PartidaController {

	private GeneralMapper<Partida, PartidaRequestDTO, PartidaResponseDTO> mapper;

	private final PartidaService service;

	public PartidaController(PartidaService service,
			GeneralMapper<Partida, PartidaRequestDTO, PartidaResponseDTO> mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<List<PartidaResponseDTO>> getAll() {
		List<Partida> partidas = service.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponseDTOList(partidas, PartidaResponseDTO.class));
	}

	@GetMapping("/confrontos")
	public ResponseEntity<ConfrontoResponseDTO> buscarConfrontos(@RequestParam Long clubeId,
			@RequestParam Long adversarioId) {
		ConfrontoResponseDTO dto = service.buscarConfrontoContra(clubeId, adversarioId);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<PartidaResponseDTO> criar(@RequestBody PartidaRequestDTO dto) {
		Partida partida = mapper.requestToModel(dto, Partida.class);
		Partida created = service.salvar(partida);
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDTO(created, PartidaResponseDTO.class));
	}
}
