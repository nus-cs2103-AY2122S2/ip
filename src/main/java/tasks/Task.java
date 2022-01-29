package tasks;

public class Task {
    protected String saveFormat;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }
    public String getSaveFormat() {
        return saveFormat;
    }
    public boolean getStatus() {
        return isDone;
    }
    public void setDone() {
        isDone = true;
    }
    public void setNotDone() {
        isDone = false;
    }
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A description of the task including its type, status, date and time.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
