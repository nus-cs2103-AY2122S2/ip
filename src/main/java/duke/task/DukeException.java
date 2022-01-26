package duke.task;

public abstract class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public abstract String toString();
}
