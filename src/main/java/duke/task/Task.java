package duke.task;

public abstract class Task {
    private final String description;
    private boolean isDone;
    private TaskType type;

    /**
     * Creates a Task with the given description and type.
     *
     * @param description The name of the task.
     * @param type The type of the task, such as TODO, DEADLINE, or EVENT.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        isDone = false;
    }

    public void mark(boolean done) {
        isDone = done;
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
        return String.format("[%s][%s] %s", getSymbol(), getStatusSymbol(), description);
    }

    private String getStatusSymbol() {
        return isDone ? "X" : " ";
    }

    private String getSymbol() {
        switch (type) {
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
