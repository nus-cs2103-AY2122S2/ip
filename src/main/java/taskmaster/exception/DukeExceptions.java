package taskmaster.exception;

/*
 * This class encapsulates DukeExceptions which handles
 * exceptions in the Duke Program.
 */

public class DukeExceptions extends Exception {

    /**
     * Constructor for the DukeExceptions class.
     *
     * @param e - Error Message
     */

    public DukeExceptions(String e) {
        super(e);
    }
}
