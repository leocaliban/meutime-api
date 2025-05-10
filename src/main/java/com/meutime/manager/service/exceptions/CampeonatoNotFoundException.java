package com.meutime.manager.service.exceptions;

public class CampeonatoNotFoundException extends ObjectNotFoundException {

	private static final long serialVersionUID = 1L;

	public CampeonatoNotFoundException(String message) {
		super(message);
	}

	public CampeonatoNotFoundException(Long id) {
		this(String.format("O Campeonato de código %d não foi encontrado.", id));
	}
}
