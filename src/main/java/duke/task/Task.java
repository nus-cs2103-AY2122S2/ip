package duke.task;


/**
 * Represents the tasks entered by users. Divided into 3 types: todo, deadline, event.
 */
public class Task {
    protected String description;
    private Boolean isDone;

    /**
     * Constructor for object Task
     *
     * @param description description for the task
     */
    public Task(String description) {
        assert(description != null && !description.equals(""));
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for object Task
     *
     * @param isDone whether the date is done or not
     * @param description description for the task
     */
    public Task(String description, int isDone) {
        assert(description != null && !description.equals(""));
        this.description = description;
        this.isDone = isDone == 1;
    }

    /**
     * Returns whether the task is done or not. True for done, false for not done.
     *
     * @return boolean value
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns status icon of the Duke.task ("X" for done, " " for not done)
     *
     * @return status icon of the Duke.task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done Duke.task with X
    }


    /**
     * Marks the Duke.task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the Duke.task as not done
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns description of the task
     *
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
