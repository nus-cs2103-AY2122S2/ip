package duke;

public class InvalidCommandFormatException extends DukeException {

    InvalidCommandFormatException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidCommandException: " + this.getMessage();
    }
}
