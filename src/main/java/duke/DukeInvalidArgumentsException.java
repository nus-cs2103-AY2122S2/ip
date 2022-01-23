package duke;

/**
 * Represents an exception that occurs as a result of a correct command entered by the user but supplied with invalid
 * arguments
 */
public class DukeInvalidArgumentsException extends DukeException {
    /**
     * Representation of a <code>DukeInvalidArgumentsException</code> object
     * @return String representation corresponding to the <code>DukeInvalidArgumentsException</code> object
     */
    @Override
    public String toString() {
        return "The arguments supplied are invalid for this command.";
    }
}
