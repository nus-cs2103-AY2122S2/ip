package main.java.ari.tasks;

/**
 * Task encapsulates information regarding task that is stored
 */
public class Task {
    protected boolean isDone = false;
    protected String taskDescription;

    /**
     * Constructor of Task
     *
     * @param description description of Task
     */
    public Task(String description) {
        this.taskDescription = description;
    }

    /**
     * Constructor of Task with no description
     */
    protected Task() {
        taskDescription = "";
    }

    /**
     * Returns string representation of Task
     *
     * @return string representation of Task
     */
    @Override
    public String toString() {
        String statusDescription = "[ ]";
        if (isDone) {
            statusDescription = "[X]";
        }

        return String.format("%s %s", statusDescription, taskDescription);
    }

    /**
     * Returns string representation of Task in save file
     *
     * @return string representation of Task in save file
     */
    public String writeToFile() {
        int bool = isDone ? 1 : 0;
        return String.format("%d %s", bool, taskDescription);
    }

    /**
     * Marks Task as done
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks Task as not done
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Returns description of Task
     *
     * @return description of Task
     */
    public String getDescription() {
        return taskDescription;
    }
}