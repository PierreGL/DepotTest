package org.pgl.mowerauto.util.exception;

/**
 * Indicates that a data file with incorrect format has been used.
 * */
public class IncorrectDataFileFormatException extends FunctionnalException {

    private static final long serialVersionUID = -8145173540370687798L;

    public IncorrectDataFileFormatException() {
        super();
    }

    public IncorrectDataFileFormatException(String message) {
        super(message);
    }

    public IncorrectDataFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataFileFormatException(Throwable cause) {
        super(cause);
    }
}
