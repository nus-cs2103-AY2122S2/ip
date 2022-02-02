package Exceptions;

/**
 * EmptyDescriptionException is a class that deals with empty description bodies followed by the
 * user inputted String
 */
public class EmptyDescriptionException extends DukeException{
    public EmptyDescriptionException(String command) {
        super("OOPS!!! The description of a " + command + " cannot be empty.");
    }
}
