package duke.task;

import duke.utils.Priority;
import duke.utils.PriorityLevel;

public abstract class Task {
    private final String description;
    private boolean isDone;
    private TaskType type;
    private Priority priority;

    /**
     * Creates a Task with the given description and type.
     *
     * @param description The name of the task.
     * @param type The type of the task, such as TODO, DEADLINE, or EVENT.
     */
    public Task(String description, TaskType type) {
        this(description, type, new Priority(PriorityLevel.LOW));
    }

    /**
     * Creates a Task with the given description, type and priority.
     *
     * @param description The name of the task.
     * @param type The type of the task, such as TODO, DEADLINE, or EVENT.
     * @param priority The Priority object specifying the priority of this Task.
     */
    public Task(String description, TaskType type, Priority priority) {
        this.description = description;
        this.type = type;
        this.priority = priority;
        isDone = false;
    }

    public void mark(boolean done) {
        isDone = done;
    }

    public void setPriority(Priority newPriority) {
        priority = newPriority;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getFileSaveFormat() {
        String doneString = isDone ? "1" : "0";
        return String.format("%s | %s | %s | %s", getSymbol(), doneString, description, priority);
    }

    public boolean hasSubstring(String searchText) {
        return description.contains(searchText);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (%s priority)", getSymbol(), getStatusSymbol(), description, priority);
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
