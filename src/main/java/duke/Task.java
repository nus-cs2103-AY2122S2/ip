package duke;

/**
 * Overarching task class
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task class
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * indicates that task is done
     */
    void markAsDone() {
        this.isDone = true;
    }

    /**
     * indicates that task is not done yet
     */
    void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Allows for the appropriate status of whether task
     * is done or not when tasks are converted to string
     * @return String indicating whether task done
     */
    public String getStatusIcon() {
        return isDone
                ? "X"
                : " ";
    }

    /**
     * Returns string representing task
     * such that it can be saved
     * @return string
     */
    public String toSave() {
        int isDoneNumber;
        if(isDone) {
            isDoneNumber = 1;
        } else {
            isDoneNumber = 0;
        }
        return " | " + isDoneNumber + " | " + description;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * to String method for Task
     * @return string representing task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
