package org.pgl.mowerauto.util.exception;

/**
 * Exception thrown in case of wrong usage of program by user.
 * */
public class FunctionnalException extends RuntimeException {

    private static final long serialVersionUID = -2747789781585106245L;

    public FunctionnalException() {
        super();
    }

    public FunctionnalException(String message) {
        super(message);
    }

    public FunctionnalException(String message, Throwable cause) {
        super(message, cause);
    }

    public FunctionnalException(Throwable cause) {
        super(cause);
    }
}
