package duke.exceptions;

/** An exception that is thrown when there are problems running
 *  the Duke program.
 */
public class DukeException extends Exception {

    /**
     * Instantiates a DukeException.
     *
     * @param errorMessage The detail message.
     * The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Default constructor that instantiates a DukeException without an error message.
     */
    public DukeException() {}

    /**
     * Returns the detail message of the error that has occurred in the program.
     *
     * @return Detail message of a DukeException.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
