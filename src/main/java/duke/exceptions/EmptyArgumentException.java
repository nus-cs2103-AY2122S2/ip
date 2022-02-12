package duke.exceptions;

/**
 * Error when there is a missing argument that is required for the execution of a command.
 */
public class EmptyArgumentException extends DukeException {
    private static final String PREFIX = "Please provide me with the ";
    private static final String CONNECTOR = " that you would like to ";
    private static final String END = "!";

    /**
     * Constructor for the exception.
     *
     * @param argument the argument that is missing from the command
     * @param verb the action of the command
     */
    public EmptyArgumentException(String argument, String verb) {
        super(PREFIX + argument + CONNECTOR + verb + END);
    }
}
