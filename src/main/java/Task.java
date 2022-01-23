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
        return this.isDone ? "X" : " ";
    }

    private String getSymbol() {
        switch (this.type) {
        case TODO:
            return "T";
        case DEADLINE:
            return "D";
        case EVENT:
            return "E";
        default:
            return " ";
        }
    };

    public String getDescription() {
        return this.description;
    }

    public String getFileSaveFormat() {
        String doneString = isDone ? "1" : "0";
        return String.format("%s | %s | %s", getSymbol(), doneString, description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getSymbol(), getStatusSymbol(), this.description);
    }
}
