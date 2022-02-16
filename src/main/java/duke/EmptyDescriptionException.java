package duke;

public class EmptyDescriptionException extends DukeException {

    EmptyDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "EmptyDescriptionException: " + this.getMessage();
    }
}
