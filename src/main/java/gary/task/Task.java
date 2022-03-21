package gary.task;

/**
 * Represents a task
 * A completed task e.g. [X] task_name
 */
public class Task {
    protected String task;
    protected boolean isDone;

    /**
     * Constructs a new Task object
     *
     * @param task task name
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Marks a Task as completed e.g. [X]
     */
    public void toMark() {
        this.isDone = true;
    }

    /**
     * Marks a Task as incomplete e.g. [ ]
     */
    public void toUnmark() {
        this.isDone = false;
    }

    /**
     * Checks if the Task matches a specific keyword
     * Invoked by gary.TaskList::find
     *
     * @param str keyword
     * @return Whether the Task contains a specific keyword
     */
    public boolean contain(String str) {
        return task.contains(str);
    }

    /**
     * Represents Task as a String object
     * Completed Task e.g. [X] task_name
     *
     * @return Task representation in String
     */
    @Override
    public String toString() {
        String done = this.isDone ? "X" : " ";
        return "[" + done + "] " + this.task;
    }
}