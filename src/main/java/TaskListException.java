/**
 * The TaskListException wraps all checked exceptions that is related to
 * user inputs to the Stevie chat bot, that is impossible for TaskList to fulfil.
 */
public class TaskListException extends StevieException {
    public TaskListException(String message) {
        super(message);
    }
}
