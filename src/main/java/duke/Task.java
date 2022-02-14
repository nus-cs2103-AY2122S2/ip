package duke;

import java.util.ArrayList;

/**
 * Represents the Task class which contains
 * the description of a specific task and its
 * status.
 */
class Task {
    protected String description;
    protected ArrayList<Boolean> isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = new ArrayList<Boolean>();
        isDone.add(false);
    }

    /**
     * Returns the status of a specific task.
     */
    public String getStatusIcon() {
        return (isDone.get(0) ? "[X]" : "[ ]"); // mark done task with X // if done is "X" then " "
    }

    /**
     * Sets a specific task to be marked.
     */
    public void setAsMarked() {
        isDone.set(0, true);
    }

    /**
     * Sets a specific task to be unmarked.
     */
    public void setAsUnmarked() {
        isDone.set(0, false);
    }

    /**
     * Returns a String which is based on the status icon of the task.
     * @param mark Mark the status of a specific task.
     */
    public String marking(String mark) {
        if (mark.equals("mark")) {
            setAsMarked();
            String messageMarked = "Nice! I've marked this task as done: \n"
                    + this.toString() + "\n ";
            return messageMarked;
        } else {
            setAsUnmarked();
            String messageUnmarked = "OK, I've marked this task as not done yet: \n"
                    + this.toString() + "\n ";
            return messageUnmarked;

        }
    }

    /**
     * Returns a String of given Task.
     */
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    /**
     * Returns the initial of the task.
     */
    public String getInitial() {
        return "Task";
    }

    /**
     * Returns the description a specific task.
     */
    public String getDescription() {
        return description;
    }
}
