package saitama.exceptions;

/**
 * An exception that is thrown when the user does not
 * specify the details of the task to be added to the task list.
 */
public class EmptyDescriptionException extends SaitamaException {
    public EmptyDescriptionException() {
        super("The task description cannot be empty.");
    }
}
