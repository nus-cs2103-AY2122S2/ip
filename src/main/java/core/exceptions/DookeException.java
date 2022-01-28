package core.exceptions;

/**
 * General exception wrapper for Dooke.
 */
public class DookeException extends RuntimeException {
    public DookeException(String message) {
        super(message);
    }

    public DookeException() {
        super();
    }
}
