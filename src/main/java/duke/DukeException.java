package duke;

/**
 * Represents an exception when user provides invalid inputs.
 *
 * @author joey-chance
 * @version 1.0
 * @since 2022-02-05
 */
public class DukeException extends Exception {
    /**
     * Returns a DukeException object with an error message to describe what caused the exception.
     *
     * @param errorMessage which describes what caused the exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
