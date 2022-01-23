package duke;

public class DukeInvalidFileSaveException extends DukeException {
    @Override
    public String toString() {
        return "File cannot be saved.";
    }
}
