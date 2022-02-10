package dooke.core.exceptions;

/**
 * General exception wrapper for dooke.Dooke.
 */
public class DookeException extends RuntimeException {
    public DookeException(String message) {
        super(message);
    }

    public DookeException() {
        super();
    }
}
