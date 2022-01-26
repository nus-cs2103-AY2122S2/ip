package duke.task;

public class ToDoIllegalArgumentException extends duke.task.DukeIllegalArgumentException {
    public ToDoIllegalArgumentException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "UH-OH!! you gotta fill in the description to create a valid todo (> <ლ)";
    }
}