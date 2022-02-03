package duke.exceptions;

/**
 * Represents an exception that is thrown during the execution of Duke.
 */
public class DukeException extends Exception {
    /**
     * Prints an error message with a warning symbol
     * ☹!!! followed by a spacing then the actual error.
     *
     * @param errorMsg error message to be printed.
     */
    public DukeException(String errorMsg) {
        super("☹!!! " + errorMsg);
    }
}
