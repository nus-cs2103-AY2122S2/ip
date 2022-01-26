package funbox.task;

//@@author junrong98-reused
//Reused from https://nus-cs2103-ay2122s2.github.io/website/admin/ip-w2.html
// with minor modifications

/**
 * The task class represents the task user's wants to do
 */
public class Task {
    public String description;
    public boolean isDone;
    public String type;

    /**
     * Constructor for the Task.
     *
     * @param description The description of the task.
     * @param type The type of task (Deadline, Event, Todo).
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * The icon representing the current status of the task
     *
     * @return Return "X" if status of task is completed else return " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Set the task to done
     */
    public void setDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    /**
     * Set the task to unfinished
     */
    public void setUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
    }

    /**
     * Sets task to done
     */
    public void presetDone() {
        this.isDone = true;
    }

    /**
     * Return a string representation.
     *
     * @return Return a string.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}