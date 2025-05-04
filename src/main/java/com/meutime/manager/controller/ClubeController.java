package com.meutime.manager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meutime.manager.entity.Clube;
import com.meutime.manager.service.ClubeService;

@RestController
@RequestMapping("/clubes")
public class ClubeController {

	private final ClubeService service;

	public ClubeController(ClubeService service) {
		this.service = service;
	}

	@GetMapping
	public List<Clube> listar() {
		return service.listar();
	}

	@PostMapping
	public Clube salvar(@RequestBody Clube clube) {
		return service.salvar(clube);
	}
}
