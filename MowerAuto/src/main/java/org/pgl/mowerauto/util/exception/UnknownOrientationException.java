package org.pgl.mowerauto.util.exception;

public class UnknownOrientationException extends TechnicalException {

	private static final long serialVersionUID = 605683840071003621L;

	public UnknownOrientationException() {
        super();
    }

    public UnknownOrientationException(String message) {
        super(message);
    }

    public UnknownOrientationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownOrientationException(Throwable cause) {
        super(cause);
    }
}
