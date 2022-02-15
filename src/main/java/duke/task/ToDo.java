package duke.task;

import duke.exception.DukeException;
import duke.exception.ErrorString;

/**
 * ToDo is a task without any date/time attached to it.
 *
 * @author yl-ang
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object containing the user specified description.
     *
     * @param description Description specified by the user.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts event object into string format for display.
     *
     * @return Formatted string of the todo description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts event object into string format for storage in duke.txt.
     *
     * @return Formatted string of the event description for storage in duke.txt.
     */
    @Override
    public String toStringForStorage() {
        int isDone = super.isDone ? 1 : 0;
        return "T | " + isDone + " | " + this.description;
    }

    @Override
    public void updateDate(String date) throws DukeException {

        if (date != null) {
            throw new DukeException(ErrorString.ERROR_TODO_HAS_NO_DATE.toString());
        }
    }
}
