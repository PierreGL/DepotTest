package org.pgl.mowerauto.util.exception;

public class UnknownDataMockTypeException extends TechnicalException {

	private static final long serialVersionUID = 4310943985234804107L;

	public UnknownDataMockTypeException() {
        super();
    }

    public UnknownDataMockTypeException(String message) {
        super(message);
    }

    public UnknownDataMockTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownDataMockTypeException(Throwable cause) {
        super(cause);
    }
}
