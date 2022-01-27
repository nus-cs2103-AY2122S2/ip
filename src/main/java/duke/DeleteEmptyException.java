package duke;

public class DeleteEmptyException extends DukeException {
    public DeleteEmptyException() {
        super("Index of task required for deletion.");
    }
}
