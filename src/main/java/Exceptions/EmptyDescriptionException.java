package Exceptions;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException (String type) {
        super("OOPS!!! The description of a " + type + " cannot be empty");
    }
}
