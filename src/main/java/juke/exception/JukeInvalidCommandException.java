package juke.exception;

public class JukeInvalidCommandException extends JukeException {
    public JukeInvalidCommandException(String cmd) {
        super(String.format("Invalid command: %s.", cmd));
    }
}
