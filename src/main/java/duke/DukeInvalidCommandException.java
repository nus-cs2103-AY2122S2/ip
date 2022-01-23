package duke;

/**
 * Represents an exception that occurs as a result of an invalid command entered by the user
 */
public class DukeInvalidCommandException extends DukeException {

    /**
     * Representation of a <code>DukeInvalidCommandException</code> object
     * @return String representation corresponding to the <code>DukeInvalidCommandException</code> object
     */
    @Override
    public String toString() {
        return "The command you've typed in is not found. Did you spell it correctly?";
    }
}
