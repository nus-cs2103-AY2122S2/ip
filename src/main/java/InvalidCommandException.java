public class InvalidCommandException extends DazzException {
    public InvalidCommandException() { super(ErrorType.INVALID_COMMAND.getErrorMessage()); }
}
