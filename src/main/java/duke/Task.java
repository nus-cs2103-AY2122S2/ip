package duke;

import java.util.ArrayList;

/**
 * Represents the Task class which contains
 * the description of a specific task and its
 * status.
 */
class Task {
    protected String description;
    protected String time;
    protected ArrayList<Boolean> isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = new ArrayList<Boolean>();
        isDone.add(false);
    }

    public Task(String description, String time) {
        this.description = description;
        this.time = time;
        this.isDone = new ArrayList<Boolean>();
        isDone.add(false);
    }

    /**
     * Returns the status of a specific task.
     * returns String which symbolises whether it has been marked.
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
     * Returns a String which indicates that the task has been marked.
     * @return String which indicates that the task has been marked.
     */
    public String mark() {
        setAsMarked();
        String messageMarked = "Nice! I've marked this task as done: \n"
                + this.toString() + "\n ";
        return messageMarked;
    }

    /**
     * Returns a String which indicates that the task has been unmarked.
     * @return String which indicates that the task has been unmarked.
     */
    public String unmark() {
        setAsUnmarked();
        String messageUnmarked = "OK, I've marked this task as not done yet: \n"
                + this.toString() + "\n ";
        return messageUnmarked;
    }

    /**
     * Returns a String of given Task.
     * @return String of the task.
     */
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    /**
     * Returns the initial of the task.
     * @return initial of the task.
     */
    public String getInitial() {
        return "Task";
    }

    /**
     * Returns the description a specific task.
     * @return description of the task.
     */
    public String getDescription() {
        return description;
    }

    public String getTime() {
        return this.time;
    }
}
