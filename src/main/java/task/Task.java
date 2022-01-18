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
    private boolean done;

    protected Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Get a checkbox describing whether the
     * task is done or not
     *
     * @return String representing done status of task
     */
    protected String getDoneStatusCheckbox() {
        return this.done ? "[X]" : "[ ]";
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    public String getName() {
        return this.name;
    }
}
