package duke.tasks;

import duke.exceptions.DukeException;

/**
 * Task is a class that manages the actions that a Task can do such as marking
 * itself as done etc.
 */
public abstract class Task {
    protected String type;
    protected String description;
    protected boolean isDone;

    /**
     * Toggles the isDone value.
     */
    public void toggleMark() {
        this.isDone = !(this.isDone);
    }

    /**
     * Returns a boolean value depending on whether the task is done,
     * if it is then the method returns true.
     * @return true if the task has been marked as done
     */
    public boolean isDone() {
        return isDone;
    }

    public abstract String getType();

    public abstract void updateDescription(String description);

    public abstract void updateDate(String newDate) throws DukeException;

    public abstract void updateTime(String newTime) throws DukeException;
}
