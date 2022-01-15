package Exceptions;

public class EmptyDescriptionException extends DukeException{
    public EmptyDescriptionException(String command) {
        super("☹ OOPS!!! The description of a " + command + " cannot be empty.");
    }
}
