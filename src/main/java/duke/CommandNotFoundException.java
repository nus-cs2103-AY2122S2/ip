package duke;

/**
 * Represents user entering command that is not recognised
 */
public class CommandNotFoundException extends DukeException {
    CommandNotFoundException(String message) {
        super(message);
    }
}
