package chatcat.chatcatexception;

/**
 * Exception handling for invalid input Date
 */
public class InvalidDateException extends Exception {
    String error;

    /**
     * Returns a exception {@code InvalidDateException} with the given message.
     */
    public InvalidDateException(String error) {
        super(error);
        this.error = error;
    }

    /**
     * Returns a error message as a String.
     *
     * @return returns a error message as a String.
     */
    @Override
    public String toString() {
        return error;
    }
}
