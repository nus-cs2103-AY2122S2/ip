package dooke.core.exceptions;

/**
 * General exception wrapper for Dooke.
 * @author s7manth
 * @version 0.3
 */
public class DookeException extends RuntimeException {
    public DookeException(String message) {
        super(message);
    }

    public DookeException() {
        super();
    }
}
