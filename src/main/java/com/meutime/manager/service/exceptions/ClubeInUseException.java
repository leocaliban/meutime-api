package com.meutime.manager.service.exceptions;

public class ClubeInUseException extends ObjectInUseException {

	private static final long serialVersionUID = 1L;

	public ClubeInUseException(String message) {
		super(message);
	}

	public ClubeInUseException(String name, Long id) {
		this(String.format("O Clube %s de código %d não pode ser removido, pois já está vinculado a uma Partida.", name, id));
	}

}
