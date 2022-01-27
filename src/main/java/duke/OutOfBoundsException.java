package duke;

public class OutOfBoundsException extends DukeException {

    OutOfBoundsException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OutOfBoundsException: " + this.getMessage();
    }
}