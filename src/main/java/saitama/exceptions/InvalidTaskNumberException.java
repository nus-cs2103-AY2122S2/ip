package saitama.exceptions;

/**
 * An exception that is thrown when an invalid task number is given to a command that requires an integer input.
 */
public class InvalidTaskNumberException extends SaitamaException {
    public InvalidTaskNumberException() {
        super("Please enter a valid task number to delete/mark/unmark!");
    }
}
