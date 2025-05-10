package com.meutime.manager.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meutime.manager.dto.request.CampeonatoRequestDTO;
import com.meutime.manager.dto.response.CampeonatoResponseDTO;
import com.meutime.manager.entity.Campeonato;
import com.meutime.manager.mappers.GeneralMapper;
import com.meutime.manager.service.CampeonatoService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {

	private final CampeonatoService service;
	private GeneralMapper<Campeonato, CampeonatoRequestDTO, CampeonatoResponseDTO> mapper;

	public CampeonatoController(CampeonatoService service,
			GeneralMapper<Campeonato, CampeonatoRequestDTO, CampeonatoResponseDTO> mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping
	public List<Campeonato> listar() {
		return service.listar();
	}

	@PostMapping
	public ResponseEntity<CampeonatoResponseDTO> salvar(@RequestBody CampeonatoRequestDTO request) {

		Campeonato campeonato = mapper.requestToModel(request, Campeonato.class);

		if (request.getEmblema() != null && !request.getEmblema().isEmpty()) {
			byte[] emblemaBytes = Base64.getDecoder().decode(request.getEmblema());
			campeonato.setEmblema(emblemaBytes);
		}

		Campeonato created = service.salvar(campeonato);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(mapper.toResponseDTO(created, CampeonatoResponseDTO.class));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
