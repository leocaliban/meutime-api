package com.meutime.manager.service.exceptions;

public abstract class ObjectInUseException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public ObjectInUseException(String message) {
		super(message);
	}

}
