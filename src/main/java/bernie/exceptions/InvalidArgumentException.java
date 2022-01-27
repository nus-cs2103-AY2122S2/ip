package bernie.exceptions;

/**
 * InvalidArgumentException is thrown whenever the user enters invalid arguments into a valid command. Such examples
 * are marking a task when it is already marked, or marking a task that doesn't exist, etc.
 */
public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
