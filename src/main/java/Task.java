public abstract class Task {
    private TaskType type;
    private String description;
    private boolean isDone;

    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public String getReadableString() {
        return String.format("[%s][%s] %s", this.type.getShorthand(), this.getStatusIcon(),
                this.description);
    }
}
