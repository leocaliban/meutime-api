package com.meutime.manager.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfrontoResponseDTO {

	private ClubeResponseDTO adversario;
	private int vitorias;
	private int derrotas;
	private int empates;
	private int partidasComPenaltis;
	private List<PartidaResponseDTO> partidas;
}
