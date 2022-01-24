/**
 * The TaskException wraps all checked exceptions that is related to
 * user inputs to the Stevie chat bot, that is impossible for TaskList to fulfil.
 */
public class TaskException extends StevieException {
    public TaskException(String message) {
        super(message);
    }
}
