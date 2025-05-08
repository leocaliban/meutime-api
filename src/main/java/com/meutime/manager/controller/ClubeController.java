package com.meutime.manager.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meutime.manager.dto.request.ClubeRequestDTO;
import com.meutime.manager.dto.response.ClubeResponseDTO;
import com.meutime.manager.entity.Clube;
import com.meutime.manager.mappers.GeneralMapper;
import com.meutime.manager.service.ClubeService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/clubes")
public class ClubeController {

	private final ClubeService service;
	private GeneralMapper<Clube, ClubeRequestDTO, ClubeResponseDTO> mapper;

	public ClubeController(ClubeService service, GeneralMapper<Clube, ClubeRequestDTO, ClubeResponseDTO> mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<List<ClubeResponseDTO>> listar() {
		List<Clube> clubes = service.listar();
		return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponseDTOList(clubes, ClubeResponseDTO.class));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClubeResponseDTO> getById(@PathVariable Long id) {
		Clube object = service.getById(id);
		return ResponseEntity.ok(mapper.toResponseDTO(object, ClubeResponseDTO.class));
	}

	@PostMapping
	public ResponseEntity<ClubeResponseDTO> salvar(@RequestBody ClubeRequestDTO request) {

		Clube clube = new Clube();
		clube.setNome(request.getNome());

		if (request.getEmblema() != null && !request.getEmblema().isEmpty()) {
			byte[] emblemaBytes = Base64.getDecoder().decode(request.getEmblema());
			clube.setEmblema(emblemaBytes);
		}

		Clube created = service.salvar(clube);

		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDTO(created, ClubeResponseDTO.class));
	}
}
