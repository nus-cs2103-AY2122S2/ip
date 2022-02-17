package duke;

/**
 * Exception class for exceptions thrown during the execution of Duke
 */
public class DukeException extends Exception {

    /**
     * Constructor for a DukeException
     *
     * @param msg Message specifying the exception thrown
     */
    public DukeException(String msg) {
        super("Uh-oh :( " + msg);
    }
}
