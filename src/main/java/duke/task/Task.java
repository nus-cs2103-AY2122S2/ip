package duke.task;

/**
 * Represents a Task. A Task object corresponds to a String description
 * and a boolean condition which represents if the task is done or not.
 */
public abstract class Task implements Comparable<Task> {

    private final String description;
    private boolean isDone;
    /**
     * Constructs a task object.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the Task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmark the task as done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }
    @Override
    public int compareTo(Task otherTask) {
        return 0;
    }
    /**
     * Returns a String representation of a Task in the desired format.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
