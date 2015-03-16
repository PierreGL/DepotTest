package org.pgl.mowerauto.util.exception;

/**
 * This exception indicates that an incorrect DataSource has been attempted to be used.
 * */
public class IncorrectDataSourceException extends TechnicalException {

    private static final long serialVersionUID = -978934525219609536L;

    public IncorrectDataSourceException() {
        super();
    }

    public IncorrectDataSourceException(String message) {
        super(message);
    }

    public IncorrectDataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataSourceException(Throwable cause) {
        super(cause);
    }
}