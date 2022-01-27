package duke.exception;

public class DeleteIndexException extends RonException {
    public static final String message = "Please add index to delete.";

    @Override
    public String toString() {
        return super.toString() + message;
    }
}
