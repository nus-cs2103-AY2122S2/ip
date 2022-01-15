public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public void mark(boolean done) {
        this.isDone = done;
    }

    private String getSymbol() {
        return this.isDone ? "\u2713" : " ";
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getSymbol(), this.description);
    }
}
