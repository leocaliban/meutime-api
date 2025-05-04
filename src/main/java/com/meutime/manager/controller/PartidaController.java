package com.meutime.manager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meutime.manager.entity.Partida;
import com.meutime.manager.repository.PartidaRepository;

@RestController
@RequestMapping("/partidas")
public class PartidaController {

    private final PartidaRepository partidaRepository;

    public PartidaController(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }

    @GetMapping
    public List<Partida> listar() {
        return partidaRepository.findAll();
    }

    @PostMapping
    public Partida criar(@RequestBody Partida partida) {
        return partidaRepository.save(partida);
    }
}
