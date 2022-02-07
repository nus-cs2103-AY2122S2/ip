package duke.exception;

/**
 * Main exception class for chatbot
 */
public class RonException extends Exception {
    public static final String DEFAULT_MESSAGE = "OH NO! ";

    @Override
    public String toString() {
        return DEFAULT_MESSAGE;
    }
}
