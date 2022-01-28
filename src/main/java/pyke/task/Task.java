package pyke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setStatus(boolean new_status) {
        this.isDone = new_status;
    }

    /**
     * Format the task class to a style used in local files for saving
     *
     * @return the formatted string for saving
     */
    public String toSavedFile() {
        if (this.isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }

    /**
     * Format the task class to a style used for output
     *
     * @return the formatted string for output
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
