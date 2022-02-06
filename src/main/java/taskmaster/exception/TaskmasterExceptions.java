package taskmaster.exception;

/*
 * This class encapsulates DukeExceptions which handles
 * exceptions in the Duke Program.
 */

public class TaskmasterExceptions extends Exception {

    /**
     * Constructor for the DukeExceptions class.
     *
     * @param e - Error Message
     */

    public TaskmasterExceptions(String e) {
        super(e);
    }
}
