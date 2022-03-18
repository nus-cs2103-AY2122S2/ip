package duke.exceptions;

/**
 * DukeException is an Exception unique to Duke.
 */
public class DukeException extends Exception {

    public static final String INVALID_COMMAND = "Command was invalid or does not exist!";
    public static final String INVALID_FORMAT = "Details input are invalid or in the wrong format!";

    /**
     * Constructs a DukeException instance with the inputted error message
     * @param errorMessage message describing the fault
     */
    public DukeException(String errorMessage) {
        super("What went wrong: " + errorMessage);
    }
}
