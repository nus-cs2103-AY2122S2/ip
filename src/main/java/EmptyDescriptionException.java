public class EmptyDescriptionException extends DazzException {
    public EmptyDescriptionException() { super(ErrorType.EMPTY_DESCRIPTION.getErrorMessage()); }
}
