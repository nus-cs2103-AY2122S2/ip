public class EmptyDateException extends DazzException {
    public EmptyDateException() { super(ErrorType.EMPTY_DATE.getErrorMessage()); }
}
