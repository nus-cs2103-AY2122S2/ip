package duke.exception;

/**
 * RonException type: Toggle failure
 * Exception thrown when user toggles mark/unmark to its original state
 */
public class ToggleException extends RonException {
    public static final String markedMessage = "Task is already marked.";
    public static final String unmarkedMessage = "Task is already unmarked.";
    public static boolean marked;

    public ToggleException(boolean marked) {
        this.marked = marked;
    }

    @Override
    public String toString() {
        if (this.marked) {
            return super.toString() + markedMessage;
        }
        return super.toString() + unmarkedMessage;
    }
}
