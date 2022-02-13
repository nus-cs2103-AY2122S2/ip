package duke.exception;

public class InvalidIndexException extends RonException {
    public static final String MESSAGE = "Please input a valid index to delete.";

    @Override
    public String toString() {
        return super.toString() + MESSAGE;
    }
}
