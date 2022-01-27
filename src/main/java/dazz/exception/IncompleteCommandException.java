package dazz.exception;

public class IncompleteCommandException extends DazzException {
    public IncompleteCommandException() { super(ErrorType.INCOMPLETE_COMMAND.getErrorMessage()); }
}
