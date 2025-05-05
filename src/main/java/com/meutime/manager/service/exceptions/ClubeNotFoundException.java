package com.meutime.manager.service.exceptions;

public class ClubeNotFoundException extends ObjectNotFoundException {

	private static final long serialVersionUID = 1L;

	public ClubeNotFoundException(String message) {
		super(message);
	}

	public ClubeNotFoundException(Long id) {
		this(String.format("O Clube de código %d não foi encontrado.", id));
	}

}
