package com.meutime.manager.service.exceptions;

public class PartidaNotFoundException extends ObjectNotFoundException {

	private static final long serialVersionUID = 1L;

	public PartidaNotFoundException(String message) {
		super(message);
	}

	public PartidaNotFoundException(Long id) {
		this(String.format("A Partida de código %d não foi encontrada.", id));
	}

}
