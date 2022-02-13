package duke.exception;

/**
 * RonException type: Toggle failure
 * Exception thrown when user toggles mark/unmark to its original state
 */
public class ToggleException extends RonException {
    public static final String MARKED_MESSAGE = "Task is already marked.";
    public static final String UNMARKED_MESSAGE = "Task is already unmarked.";
    private static boolean isMarked;

    public ToggleException(boolean isMarked) {
        this.isMarked = isMarked;
    }

    @Override
    public String toString() {
        if (this.isMarked) {
            return super.toString() + MARKED_MESSAGE;
        }
        return super.toString() + UNMARKED_MESSAGE;
    }
}
