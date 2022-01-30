package luke.data.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isFiltered;

    public Task(String description) {
        this.description = description.stripTrailing();
        this.isDone = false;
        this.isFiltered = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsFiltered() {
        isFiltered = true;
    }

    public void clearFilter() {
        isFiltered = false;
    }

    public boolean isNotFiltered() {
        return !isFiltered;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public abstract String getCommandString();
}
