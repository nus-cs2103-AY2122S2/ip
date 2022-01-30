package task;

public class Task {

    private final String description;
    private boolean completed;

    /**
     * Creates new Task object
     * @param description of task
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Creates new Task object
     * @param description of task
     * @param completed task is completed or not
     */
    public Task(String description, boolean completed) {
        this.description = description.trim();
        this.completed = completed;
    }

    /**
     * Marks the task as completed or not completed
     * @param completed States whether the task is completed or not
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * If task is completed return X else return <space>
     * @return A string of either X or <space>
     */
    public String getDescription() {
        return description;
    }

    private String getCompleted() {
        return completed ? "X" : " ";
    }

    /**
     * Returns the string of task to be place into the save file
     * @return string of task to be saved in file
     */
    public String toFile() {
        return this.completed + "\t" + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getCompleted() + "] " + this.description;
    }
}
