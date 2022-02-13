package duke.exception;

/**
 * RonException type: Toggle failure
 * Exception thrown when user toggles mark/unmark to its original state
 */
public class ToggleException extends RonException {
    public static final String MARKED_MESSAGE = "Task is already marked.";
    public static final String UNMARKED_MESSAGE = "Task is already unmarked.";
    private static boolean marked;

    public ToggleException(boolean marked) {
        this.marked = marked;
    }

    @Override
    public String toString() {
        if (this.marked) {
            return super.toString() + MARKED_MESSAGE;
        }
        return super.toString() + UNMARKED_MESSAGE;
    }
}
