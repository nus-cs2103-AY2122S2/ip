package duke;

/**
 * Represents an exception that occurs as a result of a correct command entered by the user but supplied with
 * insufficient arguments
 */
public class DukeInsufficientArgumentsException extends DukeException {

    /**
     * Representation of a <code>DukeInsufficientArgumentsException</code> object
     * @return String representation corresponding to the <code>DukeInsufficientArgumentsException</code> object
     */
    @Override
    public String toString() {
        return "The command you've typed in is missing a few arguments.";
    }
}
