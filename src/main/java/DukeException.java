public class DukeException extends Exception {
    /**
     * Public constructor, which only takes in the message.
     * @param message the message to be printed
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Public constructor, which takes in both message and error. (Unused constructor)
     * @param message the message to be printed
     * @param error the type of error that caused the exception
     */
    public DukeException(String message, Error error) {
        super(message, error);
    }
}
