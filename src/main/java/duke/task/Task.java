package duke.task;

import java.time.LocalDateTime;

import duke.exception.InvalidActionException;

/**
 * Abstract superclass of concrete classes representing Tasks that can be added to Duke.
 */
public abstract class Task {
    private static final String TASK_ALREADY_DONE_MESSAGE = "Task already done!";
    private static final String TASK_ALREADY_NOT_DONE_MESSAGE = "Task already not done!";

    protected String name;
    protected String label;
    protected boolean isDone;
    protected Reminder reminder;

    protected Task(String name, String label) {
        this(name, label, false);
    }

    protected Task(String name, String label, boolean isDone) {
        this.name = name;
        this.label = label;
        this.isDone = isDone;
    }

    /**
     * Returns a String representation of the Task.
     * Indicates the task type and whether it has been done.
     *
     * @return A String representation of the Task.
     */
    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + label + "][" + status + "] " + name;
    }

    /**
     * Returns the description of the Task.
     *
     * @return The description of the Task.
     */
    public String getDescription() {
        return this.name;
    }

    /**
     * Marks self as done.
     *
     * @throws InvalidActionException If already done.
     */
    public void markAsDone() throws InvalidActionException {
        if (isDone) {
            throw new InvalidActionException(TASK_ALREADY_DONE_MESSAGE);
        }
        isDone = true;
    }

    /**
     * Marks self as undone.
     *
     * @throws InvalidActionException If already undone.
     */
    public void markUndone() throws InvalidActionException {
        if (!isDone) {
            throw new InvalidActionException(TASK_ALREADY_NOT_DONE_MESSAGE);
        }
        isDone = false;
    }

    /**
     * Returns a String representation of the Task to be entered in the storage file.
     *
     * @return A String representation of the Task.
     */
    public String toFileFormatString() {
        if (isDone) {
            return label + " | 1 | " + name;
        }
        return label + " | 0 | " + name;
    }

    /**
     * Sets a Reminder for the Task.
     *
     * @param dateTime The time to remind the user of the Task.
     */
    public void setReminder(LocalDateTime dateTime) {
        reminder = new Reminder(dateTime);
    }

    /**
     * Returns the Reminder attached to this Task.
     *
     * @return The Reminder attached to this Task.
     */
    public Reminder getReminder() {
        return reminder;
    }
}
