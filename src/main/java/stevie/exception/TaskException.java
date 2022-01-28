package stevie.exception;

/**
 * The stevie.exception.TaskException wraps all checked exceptions that is related to
 * user inputs to the stevie.Stevie chat bot, that is impossible for stevie.task.TaskList to fulfil.
 */
public class TaskException extends StevieException {
    public TaskException(String message) {
        super(message);
    }
}
