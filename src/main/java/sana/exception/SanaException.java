package sana.exception;

/**
 * Parent class of all sana's exception
 */
public abstract class SanaException extends Exception {
    public SanaException (String message) {
        super(message);
    }
    public abstract String getMessage();
}
