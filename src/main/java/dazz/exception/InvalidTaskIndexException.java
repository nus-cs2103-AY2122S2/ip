package dazz.exception;

/**
 * Represents a <code>DazzException</code> that captures
 * invalid index of the <code>TaskList</code>.
 */
public class InvalidTaskIndexException extends DazzException {
    /**
     * Constructs an <code>InvalidTaskIndexException</code>.
     */
    public InvalidTaskIndexException() {
        super(ErrorType.INVALID_INDEX.getErrorMessage());
    }
}
