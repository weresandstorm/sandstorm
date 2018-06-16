package io.sandstorm.common.domain.repo;

public class PersistenceException extends RuntimeException {
	
	private static final long serialVersionUID = -4034470609594926978L;

	public PersistenceException() {
		super();
	}
	
	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(Throwable cause) {
		super(cause);
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
}
