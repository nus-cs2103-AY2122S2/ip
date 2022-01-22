package task;

/**
 * @author Jiaaa-yang
 *
 * A class to represent a Task
 * inputted by the ChatBot user
 */
public abstract class Task {
    /**
     * Get the string representation of current
     * task with its name and any additional info
     *
     * @return String representing current task
     */
    public abstract String getDescription();

    /**
     * String description of current task
     */
    private final String name;

    /**
     * Whether the task is marked as done
     */
    private boolean isDone;

    protected Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Get a checkbox describing whether the
     * task is done or not
     *
     * @return String representing done status of task
     */
    protected String getDoneStatusCheckbox() {
        return this.isDone ? "[X]" : "[ ]";
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }
}
