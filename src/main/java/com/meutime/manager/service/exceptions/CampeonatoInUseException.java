package com.meutime.manager.service.exceptions;

public class CampeonatoInUseException extends ObjectInUseException {

	private static final long serialVersionUID = 1L;

	public CampeonatoInUseException(String message) {
		super(message);
	}

	public CampeonatoInUseException(String name, Long id) {
		this(String.format("O Campeonato %s de código %d não pode ser removido, pois está em uso.", name, id));
	}

}
