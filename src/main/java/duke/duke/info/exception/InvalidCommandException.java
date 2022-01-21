package duke.info.exception;

import duke.utils.Text;

public class InvalidCommandException extends InvalidInputException {
    public InvalidCommandException() {
        super(Text.TEXT_INVALID_COMMAND_ERROR);
    }
}
