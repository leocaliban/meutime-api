package com.meutime.manager.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.meutime.manager.dto.request.CampeonatoRequestDTO;
import com.meutime.manager.dto.response.CampeonatoDetailsResponseDTO;
import com.meutime.manager.dto.response.CampeonatoResponseDTO;
import com.meutime.manager.entity.Campeonato;
import com.meutime.manager.entity.enums.TipoCampeonato;
import com.meutime.manager.mappers.GeneralMapper;
import com.meutime.manager.service.CampeonatoService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {

	private final CampeonatoService service;
	private GeneralMapper<Campeonato, CampeonatoRequestDTO, CampeonatoResponseDTO> mapper;
	private GeneralMapper<Campeonato, CampeonatoRequestDTO, CampeonatoDetailsResponseDTO> mapperDetails;

	public CampeonatoController(CampeonatoService service,
			GeneralMapper<Campeonato, CampeonatoRequestDTO, CampeonatoResponseDTO> mapper,
			GeneralMapper<Campeonato, CampeonatoRequestDTO, CampeonatoDetailsResponseDTO> mapperDetails) {
		this.service = service;
		this.mapper = mapper;
		this.mapperDetails = mapperDetails;
	}

	@GetMapping
	public ResponseEntity<List<CampeonatoResponseDTO>> getAll() {
		List<Campeonato> campeonatos = service.getAll();
		return ResponseEntity.status(HttpStatus.OK)
				.body(mapper.toResponseDTOList(campeonatos, CampeonatoResponseDTO.class));
	}

	@GetMapping("/details")
	public ResponseEntity<List<CampeonatoDetailsResponseDTO>> getAllDetails() {
		List<Campeonato> campeonatos = service.getAll();
		return ResponseEntity.status(HttpStatus.OK)
				.body(mapperDetails.toResponseDTOList(campeonatos, CampeonatoDetailsResponseDTO.class));
	}

	@GetMapping("/{id}/emblema")
	public ResponseEntity<byte[]> getEmblema(@PathVariable Long id) {
		Campeonato campeonatos = service.getById(id);
		if (campeonatos == null || campeonatos.getEmblema() == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().header("Content-Type", "image/png")
				.header(HttpHeaders.CACHE_CONTROL, "max-age=86400").body(campeonatos.getEmblema());
	}

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<CampeonatoDetailsResponseDTO> criarCampeonato(@RequestParam("nome") String nome,
			@RequestParam("tipo") TipoCampeonato tipo, @RequestParam("emblema") MultipartFile emblemaFile) {

		try {
			Campeonato campeonato = new Campeonato();
			campeonato.setNome(nome);
			campeonato.setTipo(tipo);
			campeonato.setEmblema(emblemaFile.getBytes());

			Campeonato created = service.salvar(campeonato);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(mapperDetails.toResponseDTO(created, CampeonatoDetailsResponseDTO.class));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
