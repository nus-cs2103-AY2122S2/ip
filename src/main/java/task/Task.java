package task;

/**
 * @author Jiaaa-yang
 *
 * A class to represent a Task
 * inputted by the ChatBot user
 */
public class Task {
    /**
     * String description of current task
     */
    private final String name;

    /**
     * Whether the task is marked as done
     */
    private boolean done;

    public Task(String task) {
        this.name = task;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    /**
     * Get the string representation of current
     * task with its done status
     *
     * @return String representing current task
     */
    public String getDescription() {
        String doneCheckBox = this.done
                ? "[X]"
                : "[ ]";
        return doneCheckBox + " " + this.name;
    }
}
