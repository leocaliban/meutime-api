package com.meutime.manager.dto.response;

import java.time.LocalDate;

import com.meutime.manager.entity.enums.Resultado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartidaResponseDTO {

	private Long id;
	private LocalDate data;
	private CampeonatoResponseDTO campeonato;
	private ClubeResponseDTO adversario;
	private ClubeResponseDTO clube;
	private boolean emCasa;
	private Integer golsClube;
	private Integer golsAdversario;
	private Resultado resultado;
	private boolean tevePenaltis;
	private Integer golsPenaltisClube;
	private Integer golsPenaltisAdversario;

}
