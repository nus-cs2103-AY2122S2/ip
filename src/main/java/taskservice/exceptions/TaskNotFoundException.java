package taskservice.exceptions;

public class TaskNotFoundException extends TaskServiceException {
    private static final String MESSAGE = "Task does not exist";

    public TaskNotFoundException() {
        super(TaskNotFoundException.MESSAGE);
    }
}
