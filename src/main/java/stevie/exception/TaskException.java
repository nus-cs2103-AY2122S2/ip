package stevie.exception;

import stevie.exception.messages.TaskExceptionMessages;

/**
 * The stevie.exception.TaskException wraps all checked exceptions that is related to
 * user inputs to the stevie.Stevie chat bot, that is impossible for stevie.task.TaskList to fulfil.
 */
public class TaskException extends StevieException {
    public TaskException(TaskExceptionMessages message) {
        super(message.toString());
    }
    public TaskException(TaskExceptionMessages message, int invalidIdx) {
        super(message.toString() + ": " + invalidIdx);
    }
}
