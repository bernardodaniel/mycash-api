package org.mycash.exception;

public class UsuarioException extends RuntimeException {

	private static final long serialVersionUID = -7277763605179286260L;

	public UsuarioException() {
		super();
	}

	public UsuarioException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsuarioException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioException(String message) {
		super(message);
	}

	public UsuarioException(Throwable cause) {
		super(cause);
	}
	
}
