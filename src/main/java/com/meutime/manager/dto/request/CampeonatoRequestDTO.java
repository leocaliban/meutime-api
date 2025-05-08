package com.meutime.manager.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampeonatoRequestDTO {

	@NotNull
	private String nome;
	
	@NotNull
	private String tipo;

	@NotNull
	private String emblema;

}
