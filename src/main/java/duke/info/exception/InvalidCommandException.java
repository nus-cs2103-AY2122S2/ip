package duke.info.exception;

import duke.utils.Text;

public class InvalidCommandException extends InvalidInputException {

    /**
     * Constructs an InvalidCommandException with the invalid command error
     * from Text.java in the utils package.
     */

    public InvalidCommandException() {
        super(Text.TEXT_INVALID_COMMAND_ERROR);
    }
}
