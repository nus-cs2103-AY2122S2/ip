package duke.tasks;

/**
 * A class that identifies a task that is required to be completed
 */
public class Task {
    public String type = "GENERAL";
    public String description;
    public Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * marks a task as completed/done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * marks a task as incomplete/undone
     */
    public void undo() {
        this.isDone = false;
    }

    public String toString() {
        String status = "[" + (isDone ? "X" : " ") + "]";
        return status + " " + this.description;
    }
}
