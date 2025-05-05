package com.meutime.manager.dto.response;

import com.meutime.manager.entity.enums.TipoCampeonato;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampeonatoResponseDTO {

	private Long id;

	private String nome;

	private TipoCampeonato tipo;
}
