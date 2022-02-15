package duke.task;

import duke.exception.DukeException;
import duke.exception.ErrorString;

/**
 * Task contains details of what the user aims to do.
 *
 * @author yl-ang
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with specified description.
     *
     * @param data Data the specified description.
     */
    public Task(String data) {
        this.description = data;
        this.isDone = false;
    }

    /**
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Changes the status of the task from unmarked to marked.
     *
     * @throws DukeException if the task is already marked as done.
     */
    public void markAsDone() throws DukeException {

        if (!this.isDone) {
            this.isDone = true;
            return;
        }
        throw new DukeException(ErrorString.ERROR_TASK_MARKED_ALREADY.toString());
    }

    /**
     * Changes the status of the task from marked to unmarked.
     *
     * @throws DukeException if the task is already marked as undone.
     */
    public void undoDone() throws DukeException {

        if (this.isDone) {
            this.isDone = false;
            return;
        }
        throw new DukeException(ErrorString.ERROR_TASK_UNMARKED_ALREADY.toString());
    }

    /**
     * Converts task object into string format for display.
     *
     * @return Formatted string of the task description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts task object into string format for storage in duke.txt.
     *
     * @return Formatted string of the task description for storage in duke.txt.
     */
    public String toStringForStorage() {
        return "";
    }

    /**
     * Updates the task description based on supplied description.
     *
     * @param description Description to update to.
     */
    public void updateDescription(String description) {

        if (!(description == null)) {
            this.description = description;
        }
    }

    public void updateDate(String date) {
    }
}
