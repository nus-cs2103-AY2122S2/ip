package bob.task;

import java.io.Serializable;

import bob.exception.BobException;
import bob.exception.PriorityException;

/**
 * Represents the task that the bob program will record.
 */
public abstract class Task implements Serializable, Comparable<Task> {
    protected static String[] statusSymbols = new String[]{"[ ]", "[✓]"};
    private String name;
    private String type;
    private Status taskStatus;
    private Priority taskPriority;
    private enum Status {
        MARKED, UNMARKED
    }
    public enum Priority {
        HIGH, MEDIUM, LOW, NONE
    }

    /**
     * Constructor for the Task object.
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        setTaskPriority(Priority.NONE.name());
    }

    public void setTaskPriority(String priority) throws BobException {
        try {
            Priority.valueOf(priority.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new PriorityException();
        }
        if (priority.equalsIgnoreCase(Priority.HIGH.name())) {
            taskPriority = Priority.HIGH;
        } else if (priority.equalsIgnoreCase(Priority.MEDIUM.name())) {
            taskPriority = Priority.MEDIUM;
        } else if (priority.equalsIgnoreCase(Priority.LOW.name())) {
            taskPriority = Priority.LOW;
        } else if (priority.equalsIgnoreCase(Priority.NONE.name())){
            taskPriority = Priority.NONE;
        }
    }

    public Priority getTaskPriority() {
        return taskPriority;
    }

    public boolean isHigh() {
        return taskPriority.equals(Priority.HIGH);
    }
    public boolean isMedium() {
        return taskPriority.equals(Priority.MEDIUM);
    }
    public boolean isLow() {
        return taskPriority.equals(Priority.LOW);
    }
    public boolean isNone() {
        return taskPriority.equals(Priority.NONE);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public boolean isMarked() {
        return taskStatus.equals(Status.MARKED);
    }

    public void unmarkTask() {
        this.taskStatus = Status.UNMARKED;
    }

    public String getStatusSymbol() {
        if (taskStatus.equals(Status.MARKED)) {
            return statusSymbols[1];
        } else {
            return statusSymbols[0];
        }
    }

    public abstract String printStatus();

    public void markTask() {
        this.taskStatus = Status.MARKED;
    }

    @Override
    public String toString() {
        return name;
    }
}
