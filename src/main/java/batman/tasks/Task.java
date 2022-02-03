package batman.tasks;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * An encapsulation of a Task to be done, that can be marked as completed.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String printNoOfTasks(int totalTasks) {
        return "Now you have " + totalTasks + " tasks in the list.";
    }

    public abstract String appendToFile();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
