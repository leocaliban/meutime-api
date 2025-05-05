package com.meutime.manager.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdRequestDTO {

	@NotNull
	private Long id;
}
