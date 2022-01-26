package Duke.task;


/**
 * Represents the tasks entered by users. Divided into 3 types: todo, deadline, event.
 */
public class Task {
    protected String description;
    public Boolean isDone;
    public String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, int isDone) {
        this.description = description;
        this.isDone = isDone == 1;
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

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}