/**
 * A task class.
 */
public class Task {
    /**
     * The description of a task.
     */
    protected String description;
    /**
     * Completion status of a task.
     */
    protected boolean done;

    /**
     * Constructs a task from given description.
     *
     * @param description description of a task
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Returns the description property of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the current task as completed.
     */
    public void markDone() {
        this.done = true;
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks the current task as undone.
     */
    public void markUndone() {
        this.done = false;
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        String status = done ? "[X]" : "[ ]";
        return status + " " + this.description;
    }
}
