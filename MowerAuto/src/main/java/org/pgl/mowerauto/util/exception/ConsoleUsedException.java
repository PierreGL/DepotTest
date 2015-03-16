package org.pgl.mowerauto.util.exception;

/**
 * Thrown when an opening console is attempted while one console system is already opened.
 * */
public class ConsoleUsedException extends TechnicalException {

    private static final long serialVersionUID = -6235065891074274101L;

    public ConsoleUsedException() {
        super();
    }

    public ConsoleUsedException(String message) {
        super(message);
    }

    public ConsoleUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleUsedException(Throwable cause) {
        super(cause);
    }

}
