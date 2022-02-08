package exception;

/**
 * A custom exception class for the Jarvis application.
 * Extends the default exception class.
 */
public class JarvisException extends Exception {
    public JarvisException() {
        super();
    }

    public JarvisException(String message) {
        super(message);
    }
}
