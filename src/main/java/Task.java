public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    // Returns the icon of the type of task
    public abstract String getIcon();

    public String getSaveString() {
        return getIcon() + "|" + (isDone ? 1 : 0) + "|" + description;
    }

    @Override
    public String toString() {
        String completionMark = (this.isDone? "X" : " ");
        return String.format("[%s][%s] %s", getIcon(), completionMark, this.description);
    }
}
