package task;

public class Todo extends Task {
    public Todo (String description, boolean isDone, Integer priorityLevel) {
        super(description, isDone, priorityLevel);
    }

    @Override
    public String fileFormat() {
        String priorityString = getPriorityLevel() == null ? "" : "| P" + getPriorityLevel();
        return String.format("T | %s | %s %s", getTaskStatus() ? "X" : " ", getDescription(), priorityString);
    }

    @Override
    public String toString() {
        String priorityString = getPriorityLevel() == null ? "" : " (Priority " + getPriorityLevel() + ") ";
        return String.format("[T][%s]%s %s", getTaskStatus() ? "X" : " ", priorityString, getDescription());
    }
}
