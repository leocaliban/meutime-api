package com.meutime.manager.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubeRequestDTO {

	@NotNull
	private String nome;
	
	@NotNull
	private String emblema;
}
