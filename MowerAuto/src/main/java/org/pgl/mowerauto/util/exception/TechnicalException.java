package org.pgl.mowerauto.util.exception;

/**
 * Exception thrown in case of wrong practice in code. 
 * */
public class TechnicalException extends RuntimeException {

    private static final long serialVersionUID = 383484433381996446L;

    public TechnicalException() {
        super();
    }

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalException(Throwable cause) {
        super(cause);
    }
}
