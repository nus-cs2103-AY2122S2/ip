package duke.exception;

import duke.exception.DukeException;

public class EmptyArgumentException extends DukeException {
    /**
     *
     * @param message
     */
    public EmptyArgumentException(String message) {
        super("☹ OOPS!!! The description of a " + message + " cannot be empty.");

    }
}