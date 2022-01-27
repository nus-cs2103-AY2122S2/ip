package duke.task;

public abstract class Task {
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

    public String getDescription() {
        return this.description;
    }

    public String getFileSaveFormat() {
        String doneString = isDone ? "1" : "0";
        return String.format("%s | %s | %s", getSymbol(), doneString, description);
    }

    public boolean hasSubstring(String searchText) {
        return description.contains(searchText);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getSymbol(), getStatusSymbol(), this.description);
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
}
