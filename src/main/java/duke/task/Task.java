package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "Unknown";
    }

    public String toString() {
        return isDone ? "[X] " + this.description : "[ ] " + this.description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Returns whether task description contains given keyword.
     *
     * @param keyword The keyword used to search for tasks.
     * @return True if the task description contains keyword, false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }
}
