package seedu.duke.exceptions;

/**
 * Thrown when the task index is invalid.
 * Thrown in commands such as {@link seedu.duke.command.MarkCommand} and {@link seedu.duke.command.DeleteCommand}
 */
public class NoValidTaskIndexException extends DukeException {
        public NoValidTaskIndexException() {
            super("Hmm the command seems to be wrong - is the task number correct?");
        }
}

