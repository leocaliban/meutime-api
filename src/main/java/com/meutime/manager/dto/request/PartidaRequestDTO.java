package com.meutime.manager.dto.request;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartidaRequestDTO {

	@NotNull
	private LocalDate data;

	@Valid
	@NotNull
	private IdRequestDTO campeonato;

	@Valid
	@NotNull
	private IdRequestDTO adversario;

	@Valid
	@NotNull
	private IdRequestDTO clube;

	@NotNull
	private boolean emCasa;

	@NotNull
	private Integer golsClube;

	@NotNull
	private Integer golsAdversario;

	@NotNull
	private boolean tevePenaltis;

	private Integer golsPenaltisClube;

	private Integer golsPenaltisAdversario;

}
