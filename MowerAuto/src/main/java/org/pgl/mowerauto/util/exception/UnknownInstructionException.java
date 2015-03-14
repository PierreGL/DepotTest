package org.pgl.mowerauto.util.exception;

public class UnknownInstructionException extends TechnicalException {

	private static final long serialVersionUID = 7956690131802522384L;

	public UnknownInstructionException() {
		super();
	}

	public UnknownInstructionException(String message) {
		super(message);
	}

	public UnknownInstructionException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownInstructionException(Throwable cause) {
		super(cause);
	}
}
