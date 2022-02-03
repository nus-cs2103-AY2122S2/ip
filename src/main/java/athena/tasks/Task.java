package athena.tasks;

public abstract class Task {
    public static final char SAVE_SEPARATOR = '|';
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    // Returns the icon of the type of task
    public abstract String getIcon();

    public String getSaveFormat() {
        return getIcon() + SAVE_SEPARATOR + (isDone ? 1 : 0) + SAVE_SEPARATOR + description;
    }

    @Override
    public String toString() {
        String completionMark = (this.isDone ? "X" : " ");
        return String.format("[%s][%s] %s", getIcon(), completionMark, this.description);
    }
}
