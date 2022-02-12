package duke.instruction;

import duke.main.DukeException;

public class IncompleteInstructionImplementationException extends DukeException {

    /**
     * Constructs an exception used by Duke, with the specified message.
     * This constructor is called by the subclasses only.
     *
     * @param message The message to be shown when the exception is thrown.
     */
    protected IncompleteInstructionImplementationException(String message) {
        super(message);
    }
}
