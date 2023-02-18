package chatcat.chatcatexception;

/**
 * Exception handling for invalid Task {@see Task} inputs
 */
public class TaskException extends Exception {
    String error;

    /**
     * Returns a exception {@code TaskException} with the given message.
     */
    public TaskException(String error) {
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
