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

import com.meutime.manager.dto.request.ClubeRequestDTO;
import com.meutime.manager.dto.response.ClubeDetailsResponseDTO;
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
	private GeneralMapper<Clube, ClubeRequestDTO, ClubeDetailsResponseDTO> mapperDetails;

	public ClubeController(ClubeService service, GeneralMapper<Clube, ClubeRequestDTO, ClubeResponseDTO> mapper,
			GeneralMapper<Clube, ClubeRequestDTO, ClubeDetailsResponseDTO> mapperDetails) {
		this.service = service;
		this.mapper = mapper;
		this.mapperDetails = mapperDetails;
	}

	@GetMapping
	public ResponseEntity<List<ClubeResponseDTO>> getAll() {
		List<Clube> clubes = service.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponseDTOList(clubes, ClubeResponseDTO.class));
	}

	@GetMapping("/details")
	public ResponseEntity<List<ClubeDetailsResponseDTO>> getAllDetails() {
		List<Clube> clubes = service.getAll();
		return ResponseEntity.status(HttpStatus.OK)
				.body(mapperDetails.toResponseDTOList(clubes, ClubeDetailsResponseDTO.class));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClubeDetailsResponseDTO> getById(@PathVariable Long id) {
		Clube object = service.getById(id);
		return ResponseEntity.ok(mapperDetails.toResponseDTO(object, ClubeDetailsResponseDTO.class));
	}

	@GetMapping("/{id}/emblema")
	public ResponseEntity<byte[]> getEmblema(@PathVariable Long id) {
		Clube object = service.getById(id);
		if (object == null || object.getEmblema() == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().header("Content-Type", "image/png")
				.header(HttpHeaders.CACHE_CONTROL, "max-age=86400").body(object.getEmblema());
	}

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<ClubeDetailsResponseDTO> criarClube(@RequestParam("nome") String nome,
			@RequestParam("emblema") MultipartFile emblemaFile) {

		try {
			Clube clube = new Clube();
			clube.setNome(nome);
			clube.setEmblema(emblemaFile.getBytes());

			Clube created = service.salvar(clube);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(mapperDetails.toResponseDTO(created, ClubeDetailsResponseDTO.class));
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
