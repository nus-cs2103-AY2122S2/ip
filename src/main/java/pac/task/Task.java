package pac.task;

import pac.PacException;

/**
 * Abstract class Task
 */
public abstract class Task {
    private final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * marks isDone as true
     * @return
     */
    Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * marks isDone as false
     * @return
     */
    Task markAsNotDone() {
        this.isDone = false;
        return this;
    }

    /**
     *
     * @return task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @return isDone
     */
    public boolean isMarked() {
        return this.isDone;
    }

    /**
     * abstract method for reschedule
     * @param dateTimeStr
     * @throws PacException
     */
    abstract public void rescheduleDate(String dateTimeStr) throws PacException;

    /**
     * abstract method for writing tasks to data file
     * @return
     */
    abstract public String toWrite();

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
