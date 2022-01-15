abstract class Task {
    private final String description;
    private boolean isDone;
    private TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public void mark(boolean done) {
        this.isDone = done;
    }

    private String getStatusSymbol() {
        return this.isDone ? "\u2713" : " ";
    }

    abstract String getSymbol();

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getSymbol(), getStatusSymbol(), this.description);
    }
}
