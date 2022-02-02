package dazz.exception;

public class InvalidDateFormatException extends DazzException {
    public InvalidDateFormatException() {
        super(ErrorType.INVALID_DATE.getErrorMessage());
    }
}
