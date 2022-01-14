/**
 * Task class represents the tasks created by the user.
 * It keeps track of the task's description and its status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor specifying the task's description and type.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon "X" of the task.
     * If the task is not done, " " is returned.
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns status icon and description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}