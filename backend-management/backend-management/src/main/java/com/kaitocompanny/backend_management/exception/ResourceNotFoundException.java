package com.kaitocompanny.backend_management.exception;

public class ResourceNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String mensagemUsuario) {
        super(mensagemUsuario);
    }
}
