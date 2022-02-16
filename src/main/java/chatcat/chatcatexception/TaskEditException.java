package chatcat.chatcatexception;

/**
 * Exception handling for invalid editing of tasks
 */
public class TaskEditException extends Exception {
    String error;

    /**
     * Returns a exception {@code TaskEditException} with the given message.
     */
    public TaskEditException(String error) {
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
