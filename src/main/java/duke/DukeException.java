package duke;

/**
 * Represents Exceptions specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates custom exception for Duke.
     *
     * @param s Error Message.
     */
    public DukeException(String s) {
        super(s);
    }
}
