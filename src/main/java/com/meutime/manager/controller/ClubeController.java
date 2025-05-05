package com.meutime.manager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meutime.manager.dto.response.ClubeResponseDTO;
import com.meutime.manager.entity.Clube;
import com.meutime.manager.mappers.GeneralMapper;
import com.meutime.manager.service.ClubeService;

@RestController
@RequestMapping("/clubes")
public class ClubeController {

	private final ClubeService service;
	private GeneralMapper<Clube, Void, ClubeResponseDTO> mapper;

	public ClubeController(ClubeService service, GeneralMapper<Clube, Void, ClubeResponseDTO> mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping
	public List<Clube> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClubeResponseDTO> getById(@PathVariable Long id) {
		Clube object = service.getById(id);
		return ResponseEntity.ok(mapper.toResponseDTO(object, ClubeResponseDTO.class));
	}

	@PostMapping
	public Clube salvar(@RequestBody Clube clube) {
		return service.salvar(clube);
	}
}
