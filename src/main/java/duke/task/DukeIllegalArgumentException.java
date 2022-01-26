package duke.task;

public abstract class DukeIllegalArgumentException extends duke.task.DukeException {
    public DukeIllegalArgumentException(String message) {
        super(message);
    }

    @Override
    public abstract String toString();
}
