package dazz.exception;

public class InvalidTaskIndexException extends DazzException {
    public InvalidTaskIndexException() {
        super(ErrorType.INVALID_INDEX.getErrorMessage());
    }
}
