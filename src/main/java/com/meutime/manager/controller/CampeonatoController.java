package com.meutime.manager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meutime.manager.entity.Campeonato;
import com.meutime.manager.service.CampeonatoService;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {

	private final CampeonatoService service;

	public CampeonatoController(CampeonatoService service) {
		this.service = service;
	}

	@GetMapping
	public List<Campeonato> listar() {
		return service.listar();
	}

	@PostMapping
	public Campeonato salvar(@RequestBody Campeonato campeonato) {
		return service.salvar(campeonato);
	}
}
